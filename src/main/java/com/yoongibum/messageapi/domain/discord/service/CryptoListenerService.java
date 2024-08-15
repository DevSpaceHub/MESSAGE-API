/*
 *
 *  © 2024 YoonGiBum, Inc. All rights reserved.
 *
 *  name : CryptoListenerService
 *  creation : 2024.8.15
 *  author : YoonGiBum
 *
 */
package com.yoongibum.messageapi.domain.discord.service;

import com.yoongibum.messageapi.common.client.NotificatorApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * The type Crypto listener service.
 */
@Slf4j
@Component
public class CryptoListenerService {
    private final NotificatorApiClient notificatorApiClient;

    /**
     * Instantiates a new Crypto listener service.
     *
     * @param notificatorApiClient the notificator api client
     */
    public CryptoListenerService(NotificatorApiClient notificatorApiClient) {
        this.notificatorApiClient = notificatorApiClient;
    }

    /**
     * Order message listen.
     *
     * @param message the message
     */
    @KafkaListener(topics = "crypto-trading", clientIdPrefix = "crypto-trading1-listener", groupId = "${spring.kafka.consumer.group-id}")
    public void orderMessageListen(Message message) {
        log.info("received crypto-trading message: {}", message.getPayload());
        notificatorApiClient.sendOrderAlarm(message.getPayload().toString());
    }
}