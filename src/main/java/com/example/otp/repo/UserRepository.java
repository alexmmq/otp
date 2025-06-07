package com.example.otp.repo;

import com.example.otp.model.Role;
import com.example.otp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
    List<UserModel> findByRoleNot(Role role);
}
