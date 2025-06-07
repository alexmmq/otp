package com.example.otp.service;

import com.example.otp.model.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smpp.Session;
import org.smpp.TCPIPConnection;
import org.smpp.pdu.BindTransmitter;
import org.smpp.pdu.SubmitSM;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Сервис рассылки SMS-сообщений
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SMSService implements ThirdPartyService{

    @Value("${notification.smpp.host}")
    private String host;
    @Value("${notification.smpp.port}")
    private int port;
    @Value("${notification.smpp.system_id}")
    private String systemId;
    @Value("${notification.smpp.password}")
    private String password;
    @Value("${notification.smpp.system_type}")
    private String systemType;
    @Value("${notification.smpp.source_addr}")
    private String sourceAddress;

    public boolean sendOTPCode(final UserModel user, final String otpCode) {
        try {
            final var connection = new TCPIPConnection(host, port);
            final var session = new Session(connection);
            final var bindRequest = new BindTransmitter();
            bindRequest.setSystemId(systemId);
            bindRequest.setPassword(password);
            bindRequest.setSystemType(systemType);
            bindRequest.setInterfaceVersion((byte) 0x34);
            bindRequest.setAddressRange(sourceAddress);
            final var bindResponse = session.bind(bindRequest);
            if (bindResponse.getCommandStatus() != 0) {
                throw new Exception("Bind failed: " + bindResponse.getCommandStatus());
            }
            final var submitSM = new SubmitSM();
            submitSM.setSourceAddr(sourceAddress);
            submitSM.setDestAddr(user.getPhone());
            submitSM.setShortMessage(String.format("OTP: %s", otpCode));

            session.submit(submitSM);

            log.info("SMS has been sent succesfully");

            return true;
        } catch (Exception e) {
            log.error("Failed to send SMS: {}", e.getMessage());
            return false;
        }
    }
}

