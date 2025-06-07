package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO request for password change")
public record PasswordChangeDTO(
        @Schema(description = "Password", example = "changeme123")
        @Size(min = 8, max = 20, message = "Password cannot exceed length of 20 and cannot be less than 8")
        String password) {
}

