package com.example.otp.service;

import com.example.otp.model.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService implements ThirdPartyService{

    private static final String SUBJECT = "OTP";

    private final JavaMailSender emailSender;

    public boolean sendOTPCode(final UserModel user, final String otpCode) {
        try {
            final var simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setSubject(SUBJECT);
            simpleMailMessage.setText(String.format("OTP: %s", otpCode));
            emailSender.send(simpleMailMessage);

            log.info("Email has been sent");

            return true;
        } catch (Exception e) {
            log.error("An Error has occurred: {}", e.getMessage());
            return false;
        }
    }
}
