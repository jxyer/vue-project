package com.jxy.blog.annotation;

import java.lang.annotation.*;

/**
 * 登录校验
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
}
