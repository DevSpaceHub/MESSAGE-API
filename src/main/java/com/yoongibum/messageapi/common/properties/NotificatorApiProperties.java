/*
 *
 *  © 2024 YoonGiBum, Inc. All rights reserved.
 *
 *  name : NotificatorApiProperties
 *  creation : 2024.7.6
 *  author : YoonGiBum
 *
 */

package com.yoongibum.messageapi.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The type Notificator api properties.
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "discord")
public class NotificatorApiProperties {
    private String webHookUrl;
}