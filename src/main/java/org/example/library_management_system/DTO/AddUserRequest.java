package org.example.library_management_system.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddUserRequest {
    String name;

    @NotNull(message = "email cannot be null")
    String email;

    String phoneNo;

    String address;
}
