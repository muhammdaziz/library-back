package com.example.libraryback.service.genre;

import com.example.libraryback.entity.Genre;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.genre.GenreAddDTO;
import com.example.libraryback.payload.genre.GenreDTO;
import com.example.libraryback.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public ApiResult<?> delete(Integer id) {

        checkExist(id);

        genreRepository.deleteById(id);

        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<List<GenreDTO>> get() {

        List<Genre> genres = genreRepository.findAll();

        return ApiResult
                .successResponse(
                        mapGenreDTO(genres));
    }

    @Override
    public ApiResult<GenreDTO> get(Integer id) {

        Genre genre = checkExist(id);

        return ApiResult
                .successResponse(
                        mapGenreDTO(genre));
    }

    @Override
    public ApiResult<GenreDTO> add(GenreAddDTO genreAddDTO) {

        checkExistByName(genreAddDTO.getName());

        Genre genre = mapGenre(genreAddDTO);

        genreRepository.save(genre);

        return ApiResult
                .successResponse(
                        mapGenreDTO(genre));
    }

    @Override
    public ApiResult<GenreDTO> edit(Integer id, GenreAddDTO genreAddDTO) {

        checkExist(id);

        checkExistByNameNotId(id, genreAddDTO.getName());

        Genre genre = mapGenre(genreAddDTO);

        genre.setId(id);

        genreRepository.save(genre);

        return ApiResult
                .successResponse(
                        mapGenreDTO(genre));
    }

    private void checkExistByNameNotId(Integer id, String name) {
        if(genreRepository.existsByNameNotId(name, id))
            throw RestException
                    .restThrow("GENRE ALREADY EXIST", HttpStatus.NOT_FOUND);
    }


    private void checkExistByName(String name) {
        if(genreRepository.existsByName(name))
            throw RestException
                    .restThrow("GENRE ALREADY EXIST", HttpStatus.CONFLICT);
    }

    private Genre checkExist(Integer id) {

        return genreRepository.findById(id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("NO GENRE FOUND", HttpStatus.NOT_FOUND));
    }

    private GenreDTO mapGenreDTO(Genre genre) {

        return GenreDTO
                .builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }

    private Genre mapGenre(GenreAddDTO genreAddDTO) {

        return Genre
                .builder()
                .name(genreAddDTO.getName())
                .build();
    }

    private List<GenreDTO> mapGenreDTO(List<Genre> genres) {

        return genres
                .stream()
                .map(this::mapGenreDTO)
                .collect(Collectors.toList());
    }
}
