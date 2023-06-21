package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.book.BookAddDTO;
import com.example.libraryback.payload.book.BookDTO;
import com.example.libraryback.payload.book.SearchDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/book")
public interface BookController {

    @GetMapping()
    ApiResult<List<BookDTO>> get();

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable UUID id);

    @GetMapping("/{id}")
    ApiResult<BookDTO> get(@PathVariable UUID id);

    @GetMapping("/search")
    ApiResult<SearchDTO> search(@RequestParam String search);

    @PostMapping(consumes = { "multipart/form-data" })
    ApiResult<BookDTO> add(@ModelAttribute BookAddDTO bookAddDTO);

    @PutMapping("/{id}")
    ApiResult<BookDTO> edit(@PathVariable UUID id, @RequestBody BookAddDTO bookAddDTO);

    @PostMapping("set-discount/{discountId}/{bookId}")
    ApiResult<BookDTO> setDiscount(@PathVariable Integer discountId, @PathVariable UUID bookId);

}
