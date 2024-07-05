package org.example.library_management_system.Repository;

import org.example.library_management_system.Model.Author;
import org.example.library_management_system.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book,Integer> , CustomBookRepository{
    Book findBookByBookNo(String BookNo);
}
