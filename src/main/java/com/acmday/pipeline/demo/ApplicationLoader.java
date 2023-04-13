package com.acmday.pipeline.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author acmday
 * @Date 2023/4/13 7:55 下午
 */
@SpringBootApplication
@Slf4j
public class ApplicationLoader {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationLoader.class, args);
        log.info("——————————————springboot success!!!——————————————");
    }
}
