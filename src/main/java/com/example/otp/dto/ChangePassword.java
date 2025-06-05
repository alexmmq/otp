package com.example.otp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Change Password Request")
public record ChangePassword (
    @Schema(description = "Password", example = "my_password")
    @Size(min = 8, max = 255, message = "Length of password should be from 8 to 255 signs")
    @NotBlank(message = "Password field cannot be empty")
    String password) {
}
