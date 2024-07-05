package org.example.library_management_system.Service;

import lombok.extern.slf4j.Slf4j;
import org.example.library_management_system.DTO.AddBookRequest;
import org.example.library_management_system.Mapper.AuthorMapper;
import org.example.library_management_system.Mapper.BookMapper;
import org.example.library_management_system.Model.Author;
import org.example.library_management_system.Model.Book;
import org.example.library_management_system.Repository.BookRepository;
import org.example.library_management_system.enums.BookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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

    public void updateBookMetaData(Book book){
        bookRepository.save(book);
    }


    public Book getBookByBookNo(String bookNo) {
        return bookRepository.findBookByBookNo(bookNo);
    }


    public List<Book> getBooks(String bookTitle, BookType bookType) {
        log.info("in the beginning of book service");
        List<Book> res = bookRepository.findBookByFilter(bookTitle,bookType);
        log.info("in the end of book service");
        return res;
    }
}
