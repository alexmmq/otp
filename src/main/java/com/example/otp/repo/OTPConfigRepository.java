package com.example.otp.repo;

import com.example.otp.model.OTPConfigModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPConfigRepository extends JpaRepository<OTPConfigModel, Long> {
}
