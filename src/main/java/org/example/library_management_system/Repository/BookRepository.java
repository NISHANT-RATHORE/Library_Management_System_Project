package org.example.library_management_system.Repository;

import org.example.library_management_system.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {

}
