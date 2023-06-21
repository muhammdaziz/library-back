package com.example.libraryback.service.author;

import com.example.libraryback.entity.Author;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.author.AuthorAddDTO;
import com.example.libraryback.payload.author.AuthorDTO;
import com.example.libraryback.repository.AuthorRepository;
import com.example.libraryback.service.io.IOService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final IOService ioService;

    private final AuthorRepository authorRepository;

    @Override
    public ApiResult<?> delete(Integer id) {

        checkExist(id);

        authorRepository.deleteById(id);

        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<List<AuthorDTO>> get() {

        List<Author> authors = authorRepository.findAll();

        return ApiResult
                .successResponse(
                        mapAuthorDTO(authors));
    }

    @Override
    public ApiResult<AuthorDTO> get(Integer id) {

        Author author = checkExist(id);

        return ApiResult
                .successResponse(
                        mapAuthorDTO(author));
    }

    @Override
    public ApiResult<AuthorDTO> add(AuthorAddDTO authorAddDTO) {

        Author author = new Author();

        mapAuthor(authorAddDTO, author);

        if (authorAddDTO.getAvatar() != null)
            try {
                author.setAvatar(
                        ioService
                                .upload(
                                        authorAddDTO.getAvatar(),
                                        true)
                );
            } catch (IOException e) {
                throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
            }


        authorRepository.save(author);

        return ApiResult
                .successResponse(
                        mapAuthorDTO(author));
    }

    @Override
    public ApiResult<AuthorDTO> edit(Integer id, AuthorAddDTO authorAddDTO) {

        Author author = checkExist(id);

        author = mapAuthor(authorAddDTO, author);

        author.setId(id);

        if (!Objects.isNull(authorAddDTO.getAvatar()))
            try {
                author.setAvatar(
                        ioService
                                .upload(
                                        authorAddDTO.getAvatar(),
                                        true)
                );
            } catch (IOException e) {
                throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
            }

        authorRepository.save(author);

        return ApiResult
                .successResponse(
                        mapAuthorDTO(author));
    }



    private Author checkExist(Integer id) {

        return authorRepository.findById(id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("NO AUTHOR FOUND", HttpStatus.NOT_FOUND));
    }

    private AuthorDTO mapAuthorDTO(Author author) {

        return AuthorDTO
                .builder()
                .id(author.getId())
                .lastname(author.getLastname())
                .firstname(author.getFirstname())
                .avatar(author.getAvatar().getId())
                .build();
    }

    private Author mapAuthor(AuthorAddDTO authorAddDTO, Author author) {

        author.setLastname(authorAddDTO.getLastname());
        author.setFirstname(authorAddDTO.getFirstname());

        return author;
    }

    private List<AuthorDTO> mapAuthorDTO(List<Author> authors) {

        return authors
                .stream()
                .map(this::mapAuthorDTO)
                .collect(Collectors.toList());
    }
}
