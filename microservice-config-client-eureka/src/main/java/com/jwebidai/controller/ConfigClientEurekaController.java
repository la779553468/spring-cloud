package com.jwebidai.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wjh on 2018/2/28.
 * 这边的@RefreshScope注解不能少，否则即使调用/refresh，配置也不会刷新
 */
@RestController
@RefreshScope
public class ConfigClientEurekaController {
    @Value("${profile}")
    private String profile;

    @GetMapping("/hello")
    public String hello() {
        return this.profile;
    }
}
