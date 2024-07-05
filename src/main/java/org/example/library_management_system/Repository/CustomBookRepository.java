package org.example.library_management_system.Repository;

import jakarta.persistence.EntityManager;
import org.example.library_management_system.Model.Book;
import org.example.library_management_system.enums.BookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository


public interface CustomBookRepository {
    List<Book> findBookByFilter(String bookTitle, BookType bookType);
}
