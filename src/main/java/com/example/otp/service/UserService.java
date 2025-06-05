package com.example.otp.service;

import com.example.otp.dto.SignUp;
import com.example.otp.dto.User;
import com.example.otp.model.Role;
import com.example.otp.model.UserModel;
import com.example.otp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserModel addUser(final SignUp signUp, final String password) {
        final var user = UserModel.builder().username(signUp.username()).password(password)
                .role(Role.USER).email(signUp.email()).phone(signUp.phone()).telegram(signUp.telegram())
                .build();
        return userRepository.save(user);
    }

    public void editUser(final UserModel user) {userRepository.save(user);}

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    public User saveUser(final User user) {
    }

    public User getActiveUser(){
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUserName(username);
    }

    private User getByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
