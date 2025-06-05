package com.example.otp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serial;
import java.io.Serializable;

public class OTPConfigModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 3L;

    @Id
    private Long id;

    @Column(nullable = false)
    private Long expiryTime;

    @Column(nullable = false)
    private Integer length;
}
