package com.example.libraryback.service.news;

import com.example.libraryback.entity.News;
import com.example.libraryback.entity.User;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.payload.UserDTO2;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.news.NewsAddDTO;
import com.example.libraryback.payload.news.NewsDTO;
import com.example.libraryback.repository.NewsRepository;
import com.example.libraryback.service.io.IOService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final IOService ioService;

    private final NewsRepository newsRepository;

    @Override
    public ApiResult<?> delete(UUID id) {

        checkExist(id);

        newsRepository.deleteById(id);

        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<NewsDTO> get(UUID id) {

        News news = checkExist(id);

        return ApiResult
                .successResponse(
                        mapNewsDTO(news));
    }

    @Override
    public ApiResult<List<NewsDTO>> get(Integer page, Integer size) {

        Page<News> news = newsRepository.findAll(PageRequest.of(page, size));

        return ApiResult
                .successResponse(
                        mapNewsDTO(news.toList()));
    }

    @Override
    public ApiResult<NewsDTO> add(NewsAddDTO newsAddDTO, User user) {

        checkExist(newsAddDTO.getTitle(), user.getId());

        News news = new News();

        mapNews(newsAddDTO, user, news);

        try {
            news.setImage(
                    ioService
                            .upload(
                                    newsAddDTO.getImage(),
                                    true)
            );
        } catch (IOException e) {
            throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
        }

        newsRepository.save(news);

        return ApiResult
                .successResponse(
                        mapNewsDTO(news));
    }

    @Override
    public ApiResult<NewsDTO> edit(UUID id, NewsAddDTO newsAddDTO, User user) {

        News news = checkExist(id);

        checkExistByTitleAndAuthorIdNOTId(id, newsAddDTO.getTitle(), user.getId());

        mapNews(newsAddDTO, user, news);

        news.setId(id);

        if (!Objects.isNull(newsAddDTO.getImage()))
            try {
                news.setImage(
                        ioService
                                .upload(
                                        newsAddDTO.getImage(),
                                        true)
                );
            } catch (IOException e) {
                throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
            }

        newsRepository.save(news);

        return ApiResult
                .successResponse(
                        mapNewsDTO(news));
    }



    private void checkExistByTitleAndAuthorIdNOTId(UUID id, String title, UUID authorId) {
        if(newsRepository.existsByTitleAndAuthorIdNotId(id, title, authorId))
            throw RestException
                    .restThrow("ALREADY EXIST WITH TITLE AND AUTHOR", HttpStatus.CONFLICT);
    }

    private void checkExist(String title, UUID authorId) {
        if (newsRepository.existsByTitleAndAuthorId(title, authorId))
            throw RestException
                    .restThrow("ALREADY EXIST WITH TITLE AND AUTHOR", HttpStatus.CONFLICT);
    }

    private News checkExist(UUID id) {

        return newsRepository.findById(id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("NO NEWS FOUND", HttpStatus.NOT_FOUND));
    }

    private NewsDTO mapNewsDTO(News news) {

        return NewsDTO
                .builder()
                .id(news.getId())
                .text(news.getText())
                .date(news.getDate())
                .title(news.getTitle())
                .image(news.getImage().getId())
                .author(UserDTO2.mapUserDTO(news.getAuthor()))
                .build();
    }

    private News mapNews(NewsAddDTO newsAddDTO, User user, News news) {

        news.setAuthor(user);
        news.setDate(new Date());
        news.setText(newsAddDTO.getText());
        news.setTitle(newsAddDTO.getTitle());

        return news;
    }

    private List<NewsDTO> mapNewsDTO(List<News> news) {

        return news
                .stream()
                .map(this::mapNewsDTO)
                .collect(Collectors.toList());
    }
}
