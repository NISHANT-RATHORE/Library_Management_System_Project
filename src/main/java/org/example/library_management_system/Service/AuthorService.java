package org.example.library_management_system.Service;

import org.example.library_management_system.Model.Author;
import org.example.library_management_system.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author getAuthorByEmail(String email){
        return authorRepository.findByEmail(email);
    }

    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }
}
