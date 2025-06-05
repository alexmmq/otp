package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="JwtAuthenticationResponse")
public record JwtAuthenticationResponse(
        @Schema(description = "Jwt Token", example = "jfreufksdklfksljfds...")
        String token) {
}
