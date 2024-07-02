package org.example.library_management_system.Controller;

import jakarta.validation.Valid;
import org.example.library_management_system.DTO.AddBookRequest;
import org.example.library_management_system.Model.Book;
import org.example.library_management_system.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody @Valid AddBookRequest addBookRequest){
        Book savedBook = bookService.addBook(addBookRequest);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }


}
