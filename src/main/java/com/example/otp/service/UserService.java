package com.example.otp.service;

import com.example.otp.dto.SignUpDTO;
import com.example.otp.dto.UserDTO;
import com.example.otp.model.UserModel;
import com.example.otp.model.Role;
import com.example.otp.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository repository;

    public UserModel addUser(final SignUpDTO request, final String encodedPassword) {

        final var user = UserModel.builder()
                .username(request.username())
                .password(encodedPassword)
                .role(Role.USER)
                .email(request.email())
                .phone(request.phone())
                .telegramChatId(request.telegramId())
                .build();

        return repository.save(user);
    }

    public void saveUser(final UserModel user) {
        repository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteUser(final Long id) {
        repository.deleteById(id);
    }

    public UserDTO saveUser(final UserDTO request) {
        final var user = getCurrentUser();

        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPhone(request.phone());
        user.setTelegramChatId(request.telegramChatId());
        repository.save(user);

        return convertToResponse(user);
    }

    public UserModel getByUsername(final String username) {
        return repository.findByUsername(username);
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public UserModel getCurrentUser() {
        final var username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return getByUsername(username);
    }

    public List<UserDTO> getAllUsers() {
        return repository.findByRoleNot(Role.ADMIN).stream()
                .map(this::convertToResponse)
                .toList();
    }

    private UserDTO convertToResponse(UserModel user) {
        return new UserDTO(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getTelegramChatId());
    }
}
