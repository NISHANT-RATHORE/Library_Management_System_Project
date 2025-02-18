package org.example.library_management_system.Controller;

import org.example.library_management_system.Model.Author;
import org.example.library_management_system.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping
    public ResponseEntity<Author> getAuthor(@RequestParam("email") String email){
        Author author = authorService.getAuthorByEmail(email);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
}
