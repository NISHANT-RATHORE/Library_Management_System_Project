package org.example.library_management_system.Controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.library_management_system.DTO.AddBookRequest;
import org.example.library_management_system.Model.Book;
import org.example.library_management_system.Service.BookService;
import org.example.library_management_system.enums.BookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody @Valid AddBookRequest addBookRequest){
        Book savedBook = bookService.addBook(addBookRequest);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(value = "title",required = false) String bookTitle,
                                               @RequestParam(value = "type",required = false)BookType bookType){
        log.info("in the getBook");
        List<Book> books = bookService.getBooks(bookTitle,bookType);
        return new ResponseEntity<>(books, HttpStatus.CREATED);
    }


}
