package com.example.otp.controller;

import com.example.otp.dto.UserDTO;
import com.example.otp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO editUser(@RequestBody @Valid UserDTO userDTO) {
        log.info("Changing of the name of user");
        return userService.saveUser(userDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getUsers() {
        log.info("List of all users, non-admins");
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        log.info("Removing of the user with id {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
