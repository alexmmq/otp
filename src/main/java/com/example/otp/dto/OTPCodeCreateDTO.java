package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO request for creating an OTP code")
public record OTPCodeCreateDTO(
        @Schema(description = "Id of operation", example = "1")
        long operationId) {
}
