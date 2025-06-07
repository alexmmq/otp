package com.example.otp.service;


import com.example.otp.dto.JwtAuthDTO;
import com.example.otp.dto.PasswordChangeDTO;
import com.example.otp.dto.SignInDTO;
import com.example.otp.dto.SignUpDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthDTO signUp(SignUpDTO request) {
        final var user = userService.addUser(request, passwordEncoder.encode(request.password()));
        final var jwt = jwtService.generateToken(user);
        return new JwtAuthDTO(jwt);
    }

    public JwtAuthDTO signIn(SignInDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        ));

        final var user = userService
                .userDetailsService()
                .loadUserByUsername(request.username());

        final var jwt = jwtService.generateToken(user);
        return new JwtAuthDTO(jwt);
    }

    public JwtAuthDTO passwordChange(PasswordChangeDTO request) {
        final var user = userService.getCurrentUser();
        final var newPassword = passwordEncoder.encode(request.password());

        user.setPassword(newPassword);
        userService.saveUser(user);

        final var jwt = jwtService.generateToken(user);
        return new JwtAuthDTO(jwt);
    }
}
