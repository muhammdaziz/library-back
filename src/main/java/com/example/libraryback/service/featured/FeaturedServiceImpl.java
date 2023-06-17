package com.example.libraryback.service.featured;

import com.example.libraryback.entity.Book;
import com.example.libraryback.entity.Featured;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.featured.FeaturedAddDTO;
import com.example.libraryback.payload.featured.FeaturedDTO;
import com.example.libraryback.repository.BookRepository;
import com.example.libraryback.repository.FeaturedRepository;
import com.example.libraryback.service.book.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeaturedServiceImpl implements FeaturedService {

    private final BookServiceImpl bookService;

    private final BookRepository bookRepository;

    private final FeaturedRepository featuredRepository;

    @Override
    public ApiResult<?> delete(Integer id) {

        checkExist(id);

        featuredRepository.deleteById(id);

        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<List<FeaturedDTO>> get() {

        List<Featured> features = featuredRepository.findByOrderByOrderNumDesc(PageRequest.of(0, 6));

        return ApiResult
                .successResponse(
                        mapFeaturedDTO(features));
    }

    @Override
    public ApiResult<FeaturedDTO> get(Integer id) {

        Featured featured = checkExist(id);

        return ApiResult
                .successResponse(
                        mapFeaturedDTO(featured));
    }

    @Override
    public ApiResult<FeaturedDTO> add(FeaturedAddDTO featuredAddDTO) {

        checkExistByBookId(featuredAddDTO.getBookId());

        Featured featured = mapFeatured(featuredAddDTO);

        featuredRepository.save(featured);

        return ApiResult
                .successResponse(
                        mapFeaturedDTO(featured));
    }

    private void checkExistByBookId(UUID bookId) {
        if (featuredRepository.existsByBookId(bookId))
            throw RestException
                    .restThrow("ALREADY EXIST WITH BOOK", HttpStatus.CONFLICT);

    }

    private Book getBook(UUID bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() ->
                        RestException
                                .restThrow("BOOK NOT FOUND", HttpStatus.NOT_FOUND));
    }

    private Featured checkExist(Integer id) {

        return featuredRepository.findById(id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("NO FEATURED BOOK FOUND", HttpStatus.NOT_FOUND));
    }

    private FeaturedDTO mapFeaturedDTO(Featured featured) {

        return FeaturedDTO
                .builder()
                .id(featured.getId())
                .book(bookService.mapBookDTO(featured.getBook()))
                .build();
    }

    private Featured mapFeatured(FeaturedAddDTO featuredAddDTO) {

        return Featured
                .builder()
                .orderNum(new Date().getTime())
                .book(getBook(featuredAddDTO.getBookId()))
                .build();
    }

    private List<FeaturedDTO> mapFeaturedDTO(List<Featured> features) {

        return features
                .stream()
                .map(this::mapFeaturedDTO)
                .collect(Collectors.toList());
    }
}
