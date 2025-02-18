package org.example.library_management_system.Mapper;

import lombok.experimental.UtilityClass;
import org.example.library_management_system.DTO.AddBookRequest;
import org.example.library_management_system.Model.Book;

@UtilityClass
public class BookMapper {
    public Book mapToBook(AddBookRequest addBookRequest){
        return Book.builder()
                .bookNo(addBookRequest.getBookNo())
                .bookTitle(addBookRequest.getBookTitle())
                .securityAmount(addBookRequest.getSecurityAmount())
                .bookType(addBookRequest.getBooktype())
                .build();
    }

}
