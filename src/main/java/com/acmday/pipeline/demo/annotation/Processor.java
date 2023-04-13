package com.acmday.pipeline.demo.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author acmday
 * @Date 2023/4/13 2:20 下午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Processor {

    String groupName();

    int order();
}