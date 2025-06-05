package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Auth Request")
public record SignIn(
        @Schema(description = "username")
        @Size(min = 1, max = 30, message = "Name must be at least 1 or no more than 30 symbols long")
        @NotBlank
        String username,
        @Schema(description = "password")
        @Size(min = 8, max = 24, message = "Password should be at least 8 or no more then 24 symbols")
        @NotBlank
        String password
) {
}
