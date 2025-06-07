package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO request on init of OTP")
public record OTPCodeInitDTO(
        @Schema(description = "OTP", example = "1234567890")
        @Size(min = 5, max = 10, message = "OTP should contain 5 to 10 symbols")
        String code) {
}
