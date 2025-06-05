package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OTP: Response")
public record OTPCode(
        @Schema(description = "ID of operation", example = "2") long id,
        @Schema(description = "Status of OTP", example = "expired") String otp) {
}
