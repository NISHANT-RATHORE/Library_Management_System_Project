package org.example.library_management_system.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.library_management_system.enums.BookType;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddBookRequest {
    @NotBlank(message = "Book title should not be blanked")
    String BookTitle;

    @NotBlank(message = "Book title should not be blanked")
    String bookNo;

    @Positive(message = "amount should be positive")
    int SecurityAmount;

    @NotNull(message = "Book type cannot be null")
    BookType booktype;

    @NotBlank(message = "Author Name should not be blanked")
    String AuthorName;

    @NotBlank(message = "Author email should not be blanked")
    String Authoremail;

}
