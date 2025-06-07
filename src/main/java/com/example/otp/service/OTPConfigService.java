package com.example.otp.service;

import com.example.otp.dto.OTPConfigDTO;
import com.example.otp.repo.OTPConfigRepository;
import com.example.otp.model.OTPConfigModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Сервис управления транзакциями
 */
@RequiredArgsConstructor
@Service
@Transactional
public class OTPConfigService {

    public static final long CONFIG_ID = 4L;

    private final OTPConfigRepository repository;

    public void updateConfiguration(final OTPConfigDTO request) {
        var config = repository.findById(CONFIG_ID)
                .orElseGet(OTPConfigModel::new);

        config.setId(CONFIG_ID);
        config.setExpirationTime(request.expirationTime());
        config.setLength(request.length());

        repository.save(config);
    }

    public OTPConfigDTO getConfiguration() {
        var config = repository.findById(CONFIG_ID).
                orElseGet(OTPConfigModel::new);
        return new OTPConfigDTO(config.getExpirationTime(), config.getLength());
    }
}
