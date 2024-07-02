package org.example.library_management_system.Mapper;

import lombok.experimental.UtilityClass;
import org.example.library_management_system.DTO.AddBookRequest;
import org.example.library_management_system.Model.Author;

@UtilityClass
public class AuthorMapper {
    public Author mapToAuthor(AddBookRequest addBookRequest){
        return Author.builder()
                .name(addBookRequest.getAuthorName())
                .email(addBookRequest.getAuthoremail())
                .build();
    }

}
