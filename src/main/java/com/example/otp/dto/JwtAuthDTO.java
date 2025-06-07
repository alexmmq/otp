package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response with Token")
public record JwtAuthDTO(
        @Schema(description = "Access Token", example = "SGskll,lfd.kllsa's'...")
        String token) {
}
