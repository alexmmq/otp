package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description="OTP: request for code initialization")
public record OTPCodeInit(
        @Schema(description = "OTP", example = "1234")
        @Size(min=4, max=10, message = "OTP should contain from 4 to 10 signs")
        @NotBlank(message = "Field cannot be empty") String code) {
}
