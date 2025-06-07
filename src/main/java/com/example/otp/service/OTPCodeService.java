package com.example.otp.service;

import com.example.otp.dto.OTPCodeCreateDTO;
import com.example.otp.dto.OTPCodeDTO;
import com.example.otp.dto.OTPCodeInitDTO;
import com.example.otp.model.OTPCodeModel;
import com.example.otp.model.Status;
import com.example.otp.repo.OTPCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.otp.model.Role;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class OTPCodeService {

    private static final String ALPHABET = "1234567890";
    private final Random random = new Random();

    private final OTPConfigService configurationService;
    private final OTPCodeRepository repository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final List<ThirdPartyService> services;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OTPCodeDTO createCode(final OTPCodeCreateDTO request) {
        var user = userService.getCurrentUser();

        var config = configurationService.getConfiguration();

        var newKey = generateKey(config.length(), value ->
                repository.existsByCodeAndStatusAndUser(value, Status.ACTIVE, user));

        var code = new OTPCodeModel();
        code.setUser(user);
        code.setStatus(Status.ACTIVE);
        code.setOperationId(request.operationId());
        code.setCode(passwordEncoder.encode(newKey));
        code.setExpirationTime(config.expirationTime());
        repository.save(code);

        var sendResult = false;
        for(var service : services) {
            sendResult |= service.sendOTPCode(user, newKey);
        }

        return convertToResponse(code);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OTPCodeDTO activateCode(final Long id, final OTPCodeInitDTO request) {
        var user = userService.getCurrentUser();

        var code = repository.findByOperationIdAndStatusAndUser(id, Status.ACTIVE, user)
                .orElseThrow(IllegalStateException::new);
        final var inputKey = passwordEncoder.encode(request.code());
        code.setStatus(Status.INACTIVE);
        repository.save(code);

        return convertToResponse(code);
    }

    public Optional<OTPCodeDTO> getCodeInfo(Long id) {
        var user = userService.getCurrentUser();
        if (Role.ADMIN.equals(user.getRole())) {
            return repository.findByOperationId(id)
                    .map(this::convertToResponse);
        } else {
            return repository.findByOperationIdAndUser(id, user)
                    .map(this::convertToResponse);
        }
    }

    public List<OTPCodeDTO> getAllCodeInfo() {
        var user = userService.getCurrentUser();
        if (Role.ADMIN.equals(user.getRole())) {
            return repository.findByStatus(Status.ACTIVE).stream()
                    .map(this::convertToResponse)
                    .toList();
        } else {
            return repository.findByStatusAndUser(Status.ACTIVE, user).stream()
                    .map(this::convertToResponse)
                    .toList();
        }
    }

    private OTPCodeDTO convertToResponse(OTPCodeModel input) {
        return new OTPCodeDTO(input.getId(), input.getStatus());
    }


    private String generateKey(final int size, final Predicate<String> existChecker) {
        String key;
        do {
            var sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
            }
            key = sb.toString();
        } while (existChecker.test(key));

        return key;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(cron = "0 * * * * *")
    @Async
    public void checkExpiringCode() {
        final var activeCodeList = repository.findByStatus(Status.ACTIVE);
        final long currentMilliseconds = System.currentTimeMillis();
        for (final var activeCode : activeCodeList) {
            if (currentMilliseconds - activeCode.getInsertTime().getTime() > activeCode.getExpirationTime()) {
                activeCode.setStatus(Status.EXPIRED);
                log.info("OTP code {} is expired", activeCode.getCode());
            }
        }
        repository.saveAll(activeCodeList);
    }
}

