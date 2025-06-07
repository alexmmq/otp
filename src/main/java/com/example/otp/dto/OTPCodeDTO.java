package com.example.otp.dto;

import com.example.otp.model.Status;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO of reponse")
public record OTPCodeDTO(
        @Schema(description = "Operation id", example = "1")
        long id,

        @Schema(description = "Status of OTP code", example = "EXPIRED")
        Status status) {
}
