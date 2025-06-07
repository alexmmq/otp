package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Schema(description = "DTO for OTP config change")
public record OTPConfigDTO(
        @Schema(description = "Life duration expectancy", example = "100000")
        @Min(value = 10000, message = "Life duration can not be less than 10 seconds")
        @Max(value = 60000, message = "Life duration cannot be more than 60 seconds")
        long expirationTime,

        @Schema(description = "Size Of OTP Code", example = "5")
        @Min(value = 5, message = "Length cannot be less than 5")
        @Max(value = 10, message = "Length cannot be bigger than 10")
        int length) {
}