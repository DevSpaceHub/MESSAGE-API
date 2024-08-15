/*
 *
 *  © 2024 YoonGiBum, Inc. All rights reserved.
 *
 *  name : DiscordDto
 *  creation : 2024.8.15
 *  author : YoonGiBum
 *
 */

package com.yoongibum.messageapi.web.external;

import lombok.Builder;
import lombok.Getter;

/**
 * The type Discord dto.
 */
@Getter
@Builder
public class DiscordDto {
    private String content;
}