package org.example.library_management_system.DTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.NotBlank;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionRequest {

    @NotBlank(message = "Book no is mandatory")
    String BookNo;

    @NotBlank(message = "User email is mandatory")
    String userEmail;
}