package org.example.library_management_system.Service;

import org.example.library_management_system.DTO.AddBookRequest;
import org.example.library_management_system.Mapper.AuthorMapper;
import org.example.library_management_system.Mapper.BookMapper;
import org.example.library_management_system.Model.Author;
import org.example.library_management_system.Model.Book;
import org.example.library_management_system.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepository bookRepository;

    public Book addBook(AddBookRequest addBookRequest) {
        Author authorFromDB = authorService.getAuthorByEmail(addBookRequest.getAuthoremail());
        if(authorFromDB == null){
            authorFromDB = AuthorMapper.mapToAuthor(addBookRequest);
            authorFromDB = authorService.addAuthor(authorFromDB);
        }
        Book book = BookMapper.mapToBook(addBookRequest);
        book.setAuthor(authorFromDB);
        return bookRepository.save(book);
    }
}
