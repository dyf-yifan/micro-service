package com.soft1851.contentcenter.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 鉴权注解
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthorization {
    String value();
}
