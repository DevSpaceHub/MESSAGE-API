/*
 *
 *  © 2024 YoonGiBum, Inc. All rights reserved.
 *
 *  name : NotificatorApiClient
 *  creation : 2024.8.15
 *  author : YoonGiBum
 *
 */

package com.yoongibum.messageapi.common.client;

import com.yoongibum.messageapi.common.excetpion.CustomException;
import com.yoongibum.messageapi.common.properties.NotificatorApiProperties;
import com.yoongibum.messageapi.web.common.ErrorResponseDto;
import com.yoongibum.messageapi.web.external.DiscordDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

/**
 * The type Notificator api client.
 */
@Slf4j
@Component
public class NotificatorApiClient {
    private WebClient webClient;

    private NotificatorApiProperties notificatorApiProperties;

    /**
     * Instantiates a new Notificator api client.
     *
     * @param notificatorApiProperties the notificator api properties
     */
    public NotificatorApiClient(NotificatorApiProperties notificatorApiProperties) {
        this.notificatorApiProperties = notificatorApiProperties;
        this.webClient = WebClient.builder().baseUrl(this.notificatorApiProperties.getWebHookUrl()).defaultHeader(CONTENT_TYPE, APPLICATION_JSON).build();
    }

    /**
     * Send order alarm.
     *
     * @param contents the contents
     */
    public void sendOrderAlarm(String contents) {
        webClient.post().contentType(MediaType.APPLICATION_JSON).bodyValue(DiscordDto.builder().content(contents).build()).retrieve().onStatus(HttpStatusCode::is4xxClientError, this::handle4xxError).onStatus(HttpStatusCode::is5xxServerError, this::handle5xxError).toBodilessEntity().block();
    }

    private Mono<Throwable> handle4xxError(ClientResponse response) {
        return response.bodyToMono(ErrorResponseDto.class).flatMap(errorResponse -> {
            log.error("4xx Error Response - name : {} message : {}", errorResponse.getError().getName(), errorResponse.getError().getMessage());
            return Mono.error((Supplier<? extends Throwable>) new CustomException(errorResponse.getError().getMessage()));
        });
    }

    private Mono<Throwable> handle5xxError(ClientResponse response) {
        return response.bodyToMono(ErrorResponseDto.class).flatMap(errorResponse -> {
            log.error("5xx Error Response - name : {} message : {}", errorResponse.getError().getName(), errorResponse.getError().getMessage());
            return Mono.error((Supplier<? extends Throwable>) new CustomException(errorResponse.getError().getMessage()));
        });
    }
}