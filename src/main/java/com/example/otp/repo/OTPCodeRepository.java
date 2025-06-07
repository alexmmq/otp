package com.example.otp.repo;

import com.example.otp.model.OTPCodeModel;
import com.example.otp.model.Status;
import com.example.otp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OTPCodeRepository extends JpaRepository<OTPCodeModel, Long> {
    Optional<OTPCodeModel> findByOperationId(Long id);
    Optional<OTPCodeModel> findByOperationIdAndUser(Long id, UserModel user);
    Optional<OTPCodeModel> findByOperationIdAndStatusAndUser(Long id, Status status, UserModel user);
    List<OTPCodeModel> findByStatus(Status status);
    List<OTPCodeModel> findByStatusAndUser(Status status, UserModel user);
    boolean existsByOperationIdAndStatusAndUser(Long id, Status status, UserModel user);
    boolean existsByCodeAndStatusAndUser(String code, Status status, UserModel user);
}
