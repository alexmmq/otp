package com.example.otp.repository;

import com.example.otp.dto.OTPConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPConfigRepository extends JpaRepository<OTPConfig, Long> {
}
