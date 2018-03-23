package com.zr.common;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by Miste on 3/22/2018.
 *
 * 添加自定义注解，在需要进行权限认证的方法上使用此注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface Authentication {
    String value() default "";
}
