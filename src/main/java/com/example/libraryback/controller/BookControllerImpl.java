package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.book.BookAddDTO;
import com.example.libraryback.payload.book.BookDTO;
import com.example.libraryback.payload.book.SearchDTO;
import com.example.libraryback.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Override
    public ApiResult<List<BookDTO>> get(){
        return bookService.get();
    }

    @Override
    public ApiResult<?> delete(UUID id){
        return bookService.delete(id);
    }

    @Override
    public ApiResult<BookDTO> get(UUID id){
        return bookService.get(id);
    }

    @Override
    public ApiResult<SearchDTO> search(String search) {
        return bookService.search(search);
    }

    @Override
    public ApiResult<BookDTO> add(BookAddDTO bookAddDTO){
        return bookService.add(bookAddDTO);
    }

    @Override
    public ApiResult<BookDTO> edit(UUID id, BookAddDTO bookAddDTO){
        return bookService.edit(id, bookAddDTO);
    }

    @Override
    public ApiResult<BookDTO> setDiscount(Integer discountId, UUID bookId) {
        return bookService.setDiscount(discountId, bookId);
    }

}
