package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "UserDTO request")
public record UserDTO(
        @Schema(description = "Id of the user", example = "1")
        long id,

        @Schema(description = "Name")
        @Size(min = 1, max = 30, message = "Name of the user should be at least 1 symbol long and be not bigger than 30")
        String username,

        @Schema(description = "Email", example = "email@example.com")
        String email,

        @Schema(description = "Phone of the user")
        @Pattern(regexp = "^(8|\\+7)\\d{10}",
                message = "Phone number should be formatted properly")
        String phone,

        @Schema(description = "Telegram Id")
        String telegramChatId) {
}
