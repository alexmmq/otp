package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "User request")
public record User(
        @Schema(description = "User Id")
        @Nullable
        long id,
        @Schema(description = "username")
        @Size(min = 1, max = 30, message = "Name must be at least 1 or no more than 30 symbols long")
        @NotBlank
        String username,
        @Schema(description = "the email of user")
        @Email(message = "Email ID should be valid")
        @NotBlank
        String email,
        @Schema(description = "phone of the user")
        @Pattern(regexp = "^(8|\\+7)\\d{10}", message = "should be a valid phone format")
        @NotBlank
        String phone,
        @Schema(description = "Telegram ID")
        @NotBlank
        String telegram
) {
}
