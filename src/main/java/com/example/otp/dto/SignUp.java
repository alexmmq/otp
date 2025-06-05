package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "SignUp Request")
public record SignUp(
        @Schema(description = "name")
        @Size(min = 1, max = 30, message = "Name of the user should contain from 1 to 30 symbols")
        @NotBlank(message = "Name of the user should not be empty")
        String username,
        @Schema(description = "password")
        @Size(min = 8, max = 24, message = "Password should be at least 8 or no more then 24 symbols")
        @NotBlank
        String password,
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
        String telegram) {
}
