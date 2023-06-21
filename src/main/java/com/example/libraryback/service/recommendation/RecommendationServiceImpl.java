package com.example.libraryback.service.recommendation;

import com.example.libraryback.entity.Book;
import com.example.libraryback.entity.Recommendation;
import com.example.libraryback.entity.RecommendationBook;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.recommendation.RecommendationAddDTO;
import com.example.libraryback.payload.recommendation.RecommendationDTO;
import com.example.libraryback.repository.BookRepository;
import com.example.libraryback.repository.RecommendationBookRepository;
import com.example.libraryback.repository.RecommendationRepository;
import com.example.libraryback.service.book.BookServiceImpl;
import com.example.libraryback.service.io.IOService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final IOService ioService;

    private final BookServiceImpl bookService;

    private final BookRepository bookRepository;

    private final RecommendationRepository recommendationRepository;

    private final RecommendationBookRepository recommendationBookRepository;

    @Override
    public ApiResult<?> delete(Integer id){

        checkExist(id);

        recommendationRepository.deleteById(id);

        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<List<RecommendationDTO>> get(){

        List<Recommendation> recommendations = recommendationRepository.findAll();

        return ApiResult
                .successResponse(
                        mapRecommendationDTO(recommendations));
    }

    @Override
    public ApiResult<RecommendationDTO> get(Integer id){

        Recommendation recommendation = checkExist(id);

        return ApiResult
                .successResponse(
                        mapRecommendationDTO(recommendation));
    }

    @Override
    public ApiResult<RecommendationDTO> add(RecommendationAddDTO recommendationAddDTO){

        checkExistByTitle(recommendationAddDTO.getTitle());

        checkBooksAreExist(recommendationAddDTO.getBooks());

        Recommendation recommendation = new Recommendation();

        mapRecommendation(recommendationAddDTO, recommendation);

        try {
            recommendation.setBackgroundImage(
                    ioService
                            .upload(
                                    recommendationAddDTO.getBackgroundImage(),
                                    true)
            );
        } catch (IOException e) {
            throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
        }

        recommendation = recommendationRepository.save(recommendation);

        saveRecommendationBook(recommendation, recommendationAddDTO.getBooks());

        return ApiResult
                .successResponse(
                        mapRecommendationDTO(recommendation));
    }

    private void checkBooksAreExist(List<UUID> books) {
        if (!bookRepository.existsAllByIdIn(books))
            throw RestException
                    .restThrow("BOOK NOT FOUND", HttpStatus.NOT_FOUND);
    }

    private void saveRecommendationBook(Recommendation recommendation, List<UUID> books) {

        recommendationBookRepository.saveAll(books
                .stream()
                .map(bookId -> RecommendationBook
                        .builder()
                        .book(bookRepository
                                .findById(bookId)
                                .orElseThrow(() ->
                                        RestException
                                                .restThrow("BOOK NOT FOUND", HttpStatus.NOT_FOUND)))
                        .recommendation(recommendation)
                        .build())
                .collect(Collectors.toList())
        );
    }

    @Override
    public ApiResult<RecommendationDTO> edit(Integer id, RecommendationAddDTO recommendationAddDTO){

        Recommendation recommendation = checkExist(id);

        checkExistByTitleNotId(id, recommendationAddDTO.getTitle());

        checkBooksAreExist(recommendationAddDTO.getBooks());

        mapRecommendation(recommendationAddDTO, recommendation);

        if (!Objects.isNull(recommendationAddDTO.getBackgroundImage()))
            try {
                recommendation.setBackgroundImage(
                        ioService
                                .upload(
                                        recommendationAddDTO.getBackgroundImage(),
                                        true)
                );
            } catch (IOException e) {
                throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
            }

        recommendationRepository.save(recommendation);

        return ApiResult
                .successResponse(
                        mapRecommendationDTO(recommendation));
    }



    private void checkExistByTitle(String title) {

        if(recommendationRepository.existsByTitle(title))
            throw RestException
                                .restThrow("RECOMMENDATION ALREADY EXIST", HttpStatus.CONFLICT);
    }

    private List<Book> getBooks(Integer recommendationId) {
        return bookRepository.findAllByRecommendationId(recommendationId);
    }

    private Recommendation checkExist(Integer id) {

        return recommendationRepository.findById(id)
                .orElseThrow(
                        () -> RestException
                                .restThrow("NO PROMOTION FOUND", HttpStatus.NOT_FOUND));
    }

    private void checkExistByTitleNotId(Integer id, String title) {

        recommendationRepository.findByTitleNotId(title, id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("RECOMMENDATION TITLE EXIST", HttpStatus.CONFLICT));
    }

    private RecommendationDTO mapRecommendationDTO(Recommendation recommendation) {

        return RecommendationDTO
                .builder()
                .id(recommendation.getId())
                .title(recommendation.getTitle())
                .subtitle(recommendation.getSubtitle())
                .bkgImage(recommendation.getBackgroundImage().getId())
                .books(bookService.mapBookDTO(getBooks(recommendation.getId())))
                .build();
    }

    private void mapRecommendation(RecommendationAddDTO recommendationAddDTO, Recommendation recommendation) {

        recommendation.setTitle(recommendationAddDTO.getTitle());
        recommendation.setSubtitle(recommendationAddDTO.getSubtitle());
    }

    private List<RecommendationDTO> mapRecommendationDTO(List<Recommendation> recommendations) {

        return recommendations
                .stream()
                .map(this::mapRecommendationDTO)
                .collect(Collectors.toList());
    }
}
