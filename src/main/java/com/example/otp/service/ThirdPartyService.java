package com.example.otp.service;

import com.example.otp.model.UserModel;

public interface ThirdPartyService {
    boolean sendOTPCode(UserModel user, String otpCode);
}
