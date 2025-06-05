package com.example.otp.repository;

import com.example.otp.dto.OTPCode;
import com.example.otp.dto.User;
import com.example.otp.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OTPCodeRepository extends JpaRepository<OTPCode, Long> {
    Optional<OTPCode> findByOperationId(Long operationId);
    Optional<OTPCode> findByOperationIdAndStatusAndUserId(Long operationId, Status status, User user);
    List<OTPCode> findByStatus(Status status);
    List<OTPCode> findByUserId(User user);
}
