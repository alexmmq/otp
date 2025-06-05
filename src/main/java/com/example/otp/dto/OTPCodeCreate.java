package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "OTP: creation of code")
public record OTPCodeCreate(
        @Schema(description = "ID of the operation", example = "2")
        @NotNull(message = "Field shouldn't be empty") long operationId) {
}
