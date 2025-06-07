package com.example.otp.controller;

import com.example.otp.dto.JwtAuthDTO;
import com.example.otp.dto.PasswordChangeDTO;
import com.example.otp.dto.SignInDTO;
import com.example.otp.dto.SignUpDTO;
import com.example.otp.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "REST API: Аутентификация")
public class AuthController {

    private final AuthService authenticationService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/signup",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public JwtAuthDTO signUp(@RequestBody @Valid SignUpDTO request) {
        log.info("User registration {}", request.username());
        return authenticationService.signUp(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/signin",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public JwtAuthDTO signIn(@RequestBody @Valid SignInDTO request) {
        log.info("User authorization {}", request.username());
        return authenticationService.signIn(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/passwordchange",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public JwtAuthDTO passwordChange(@RequestBody @Valid PasswordChangeDTO request) {
        log.info("Changing of the password");
        return authenticationService.passwordChange(request);
    }
}
