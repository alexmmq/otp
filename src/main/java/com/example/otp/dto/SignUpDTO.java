package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Registration request")
public record SignUpDTO(
        @Schema(description = "Name")
        @Size(min = 1, max = 30, message = "Name of the user should be at least 1 symbol long and be not bigger than 30")
        String username,

        @Schema(description = "Password", example = "changeme123")
        @Size(min = 8, max = 20, message = "Password length should not be bigger than 20 and less than 8")
        String password,

        @Schema(description = "Email", example = "email@example.com")
        String email,

        @Schema(description = "Phone of the user")
        @Pattern(regexp = "^(8|\\+7)\\d{10}",
                message = "Phone number should be formatted properly")
        String phone,

        @Schema(description = "Telegram Id")
        String telegramId) {
}
