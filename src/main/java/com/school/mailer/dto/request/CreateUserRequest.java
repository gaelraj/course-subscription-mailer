package com.school.mailer.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
    @NotBlank(message = "The first name is mandatory") String firstName,
    @NotBlank(message = "The last name is mandatory") String lastName,
    @NotBlank(message = "The username is mandatory") String userName,
    @NotBlank(message = "The email is mandatory") @Email(message = "The email must be valid")
        String email) {}
