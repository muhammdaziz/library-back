package com.example.libraryback.service.book;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.book.BookAddDTO;
import com.example.libraryback.payload.book.BookDTO;

import java.util.List;
import java.util.UUID;

public interface BookService {

    ApiResult<?> delete(UUID id);

    ApiResult<List<BookDTO>> get();

    ApiResult<BookDTO> get(UUID id);

    ApiResult<BookDTO> add(BookAddDTO bookAddDTO);

    ApiResult<BookDTO> edit(UUID id, BookAddDTO bookAddDTO);

    ApiResult<BookDTO> setDiscount(Integer discountId, UUID bookId);

}
