package org.example.library_management_system.Repository;

import org.example.library_management_system.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    // both work in same manner
    @Query(name = "select * from Author where email :=email", nativeQuery = true)
    Author getAuthorByEmail(String email);

    Author findByEmail(String email);
}