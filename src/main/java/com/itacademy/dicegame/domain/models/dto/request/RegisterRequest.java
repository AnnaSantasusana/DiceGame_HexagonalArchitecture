package com.itacademy.dicegame.domain.models.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    @NotBlank(message = "The email cannot be blank")
    @Email(message = "The email format is invalid")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "The password must be at least 6 characters long")
    private String password;
}
