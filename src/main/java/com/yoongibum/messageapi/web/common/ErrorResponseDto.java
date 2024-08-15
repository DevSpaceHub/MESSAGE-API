/*
 *
 *  © 2024 YoonGiBum, Inc. All rights reserved.
 *
 *  name : ErrorResponseDto
 *  creation : 2024.8.15
 *  author : YoonGiBum
 *
 */

package com.yoongibum.messageapi.web.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Error response dto.
 */
@Getter
@Setter
public class ErrorResponseDto implements Serializable {
    private static final long serialVersionUID = -3527815064890406675L;

    private Error error;
}
