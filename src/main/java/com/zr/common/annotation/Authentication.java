package com.zr.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by Miste on 3/22/2018.
 *
 * 自定义注解
 * 在需要进行权限认证的方法上使用此注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface Authentication {
    String value() default "";
}
