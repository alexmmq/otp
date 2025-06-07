package com.example.otp.controller;


import com.example.otp.dto.OTPCodeCreateDTO;
import com.example.otp.dto.OTPCodeDTO;
import com.example.otp.dto.OTPCodeInitDTO;
import com.example.otp.dto.OTPConfigDTO;
import com.example.otp.service.OTPCodeService;
import com.example.otp.service.OTPConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("otp")
public class OtpCodeController {

    private final OTPConfigService otpConfigurationService;
    private final OTPCodeService otpCodeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public OTPCodeDTO createCode(@RequestBody @Valid OTPCodeCreateDTO code) {
        log.info("Creation of OTP Code {}", code.operationId());
        return otpCodeService.createCode(code);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/{id}/init",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public OTPCodeDTO useCode(@PathVariable("id") Long id, @RequestBody @Valid OTPCodeInitDTO code) {
        log.info("Initialization of OTP Code of operation {}", id);
        return otpCodeService.activateCode(id, code);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OTPCodeDTO> getAllCodes() {
        log.info("Getting a list of all OTP codes");
        return otpCodeService.getAllCodeInfo();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/configuration/update",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public OTPConfigDTO updateConfiguration(@RequestBody @Valid OTPConfigDTO configuration) {
        log.info("Changing of configuration");
        otpConfigurationService.updateConfiguration(configuration);
        return configuration;
    }
}
