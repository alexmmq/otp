package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description ="OTP config")
public record OTPConfig(
        @Schema(description = "Lifetime of OTP in milliseconds")
        @Min(value = 60000, message = "Lifetime of OTP cannot be less than 60 seconds")
        @Max(value = 180000, message = "Lifetime of OTP cannot be bigger than 3 minutes")
        @NotNull
        long timeout,
        @Schema(description = "Size of the OTP code")
        @Min(value = 4, message = "Size of OTP cannot be less than 4")
        @Max(value = 10, message = "Size of OTP cannot be bigger than 10")
        @NotNull
        int length
) {

}
