package com.example.libraryback.service.book;

import com.example.libraryback.entity.*;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.author.AuthorDTO;
import com.example.libraryback.payload.book.BookAddDTO;
import com.example.libraryback.payload.book.BookDTO;
import com.example.libraryback.payload.book.SearchDTO;
import com.example.libraryback.payload.discount.DiscountDTO;
import com.example.libraryback.repository.*;
import com.example.libraryback.service.io.IOService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final IOService ioService;
    private final BookRepository bookRepository;
    private final LikeRepository likeRepository;
    private final GenreRepository genreRepository;
    private final ReviewRepository reviewRepository;
    private final AuthorRepository authorRepository;
    private final DiscountRepository discountRepository;
    private final BookGenreRepository bookGenreRepository;

    @Override
    public ApiResult<?> delete(UUID id){

        checkExist(id);

        bookRepository.deleteById(id);

        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<List<BookDTO>> get(){

        List<Book> books = bookRepository.findAll();

        return ApiResult
                .successResponse(
                        mapBookDTO(books));
    }

    @Override
    public ApiResult<BookDTO> get(UUID id){

        Book book = checkExist(id);

        return ApiResult
                .successResponse(
                        mapBookDTO(book));
    }

    @Override
    public ApiResult<SearchDTO> search(String value) {
//        Page<Book> books = bookRepository.search(value, PageRequest.of(0, 10));
        Page<Book> books = bookRepository.findByTitleContainingIgnoreCaseOrPublisherContainingIgnoreCase(value, value, PageRequest.of(0, 10));


        return ApiResult
                .successResponse(mapSearch(books.toList()));
    }

    private SearchDTO mapSearch(List<Book> books) {
        return SearchDTO
                .builder()
                .books(mapBookDTO(books))
                .build();
    }

    @Override
    public ApiResult<BookDTO> add(BookAddDTO bookAddDTO){

        checkAuthorExist(bookAddDTO.getAuthorId());

        checkGenresExist(bookAddDTO.getGenres());

        checkExistByTitleAndAuthorId(bookAddDTO.getTitle(), bookAddDTO.getAuthorId());

        Book book = new Book();

        mapBook(bookAddDTO, book);

        try {
            book.setDocument(
                    ioService
                            .upload(
                                    bookAddDTO.getDocument(),
                                    false)
            );

            book.setImage(
                    ioService
                            .upload(
                                    bookAddDTO.getImage(),
                                    true
                            )
            );
        } catch (IOException e) {
            throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
        }

        book = bookRepository.save(book);

        saveBookGenre(bookAddDTO.getGenres(), book);

        return ApiResult
                .successResponse(
                        mapBookDTO(book));
    }

    @Override
    public ApiResult<BookDTO> edit(UUID id, BookAddDTO bookAddDTO){

        Book book = checkExist(id);

        mapBook(bookAddDTO, book);

        book.setId(id);

        try {
            if (!Objects.isNull(bookAddDTO.getImage()))
                book.setImage(
                        ioService
                                .upload(
                                        bookAddDTO.getImage(),
                                        true)
                );

            if (!Objects.isNull(bookAddDTO.getDocument()))
                book.setDocument(
                        ioService
                                .upload(
                                        bookAddDTO.getDocument(),
                                        false)
                );
            } catch (IOException e) {
                throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
            }

        bookRepository.save(book);

        return ApiResult
                .successResponse(
                        mapBookDTO(book));
    }

    @Override
    public ApiResult<BookDTO> setDiscount(Integer discountId, UUID bookId) {

        Discount discount = getDiscountById(discountId);

        Book book = getBookById(bookId);

        book.setDiscount(discount);

        bookRepository.save(book);

        return ApiResult
                .successResponse(
                        mapBookDTO(book));
    }


    public BookDTO mapBookDTO(Book book) {

        return BookDTO
                .builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .price(book.getPrice())
                .title(book.getTitle())
                .language(book.getLanguage())
                .image(book.getImage().getId())
                .publisher(book.getPublisher())
                .points(getPoints(book.getId()))
                .genres(getGenres(book.getId()))
                .description(book.getDescription())
                .document(book.getDocument().getId())
                .likeCount(getLikeCount(book.getId()))
                .editionFormat(book.getEditionFormat())
                .publishedDate(book.getPublishedDate())
                .author(AuthorDTO.map(book.getAuthor()))
                .reviewCount(getReviewCount(book.getId()))
                .discount(DiscountDTO.map(book.getDiscount()))
                .build();
    }

    public List<BookDTO> mapBookDTO(List<Book> books) {

        return books
                .stream()
                .map(this::mapBookDTO)
                .collect(Collectors.toList());
    }

    public Book getBookById(UUID bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() ->
                        RestException
                                .restThrow("BOOK NOT FOUND", HttpStatus.NOT_FOUND));
    }

    private Discount getDiscountById(Integer discountId) {
        return discountRepository.findById(discountId)
                .orElseThrow(() ->
                        RestException
                                .restThrow("DISCOUNT NOT FOUND", HttpStatus.NOT_FOUND));
    }

    private void checkGenresExist(List<Integer> genres) {

        if(!genreRepository.existsAllByIdIn(genres))
            throw RestException
                    .restThrow("GENRE NOT FOUND", HttpStatus.NOT_FOUND);
    }

    private void saveBookGenre(List<Integer> genres, Book book) {
        List<BookGenre> bookGenres = genres
                .stream()
                .distinct()
                .map(genreId ->
                        BookGenre
                                .builder()
                                .book(book)
                                .genre(getGenre(genreId))
                                .build())
                .collect(Collectors.toList());

        bookGenreRepository.saveAll(bookGenres);
    }

    private Genre getGenre(Integer genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() ->
                        RestException
                                .restThrow("GENRE NOT FOUND", HttpStatus.NOT_FOUND));
    }

    private Book checkExist(UUID id) {

        return bookRepository.findById(id)
                .orElseThrow(
                        () -> RestException
                                .restThrow("NO BOOK FOUND", HttpStatus.NOT_FOUND)
                );
    }

    private Double getPoints(UUID bookId) {

        List<Review> reviews = reviewRepository.findAllByBookId(bookId);

        if (reviews.size() == 0)
            return 0.0;

        final Float[] count = {0F};

        reviews.forEach(review ->
                count[0] += review.getPoint()
        );

        double res = count[0] / reviews.size();

        res = res * Math.pow(10, 1);
        res = Math.floor(res);
        res = res / Math.pow(10, 1);

        return res;
    }


    private List<String> getGenres(UUID bookId) {

        List<BookGenre> bookGenres = bookGenreRepository.findAllByBookId(bookId);

        return bookGenres
                .stream()
                .map(bookGenre ->
                        bookGenre
                                .getGenre().getName())
                .collect(Collectors.toList());
    }

    private Integer getLikeCount(UUID bookId) {
        return likeRepository.countByBookId(bookId);
    }

    private Author getAuthor(Integer authorId) {

        return authorRepository
                .findById(authorId)
                .orElseThrow(
                        () -> RestException
                                .restThrow("AUTHOR NOT FOUND", HttpStatus.CONFLICT));
    }

    private Integer getReviewCount(UUID bookId) {
        return reviewRepository.countByBookId(bookId);
    }

    private void mapBook(BookAddDTO bookAddDTO, Book book) {

        book.setIsbn(bookAddDTO.getIsbn());
        book.setPrice(bookAddDTO.getPrice());
        book.setTitle(bookAddDTO.getTitle());
        book.setLanguage(bookAddDTO.getLanguage());
        book.setPublisher(bookAddDTO.getPublisher());
        book.setDescription(bookAddDTO.getDescription());
        book.setAuthor(getAuthor(bookAddDTO.getAuthorId()));
        book.setEditionFormat(bookAddDTO.getEditionFormat());
        book.setPublishedDate(bookAddDTO.getPublishedDate());

    }

    private void checkAuthorExist(Integer authorId) {
        if(!authorRepository.existsById(authorId))
            throw RestException
                    .restThrow("AUTHOR NOT FOUND", HttpStatus.NOT_FOUND);
    }

    private void checkExistByTitleAndAuthorId(String title, Integer authorId) {

        if(bookRepository.existsByTitleAndAuthorId(title, authorId))
                throw RestException
                        .restThrow("BOOK ALREADY EXIST", HttpStatus.CONFLICT);
    }

}
