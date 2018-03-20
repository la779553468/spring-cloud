package com.jwebidai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by wjh on 2018/3/8.
 */
@Component("redisConfig")
@PropertySource("config.properties")
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {
    private String ip;
    private Integer port;
    private Integer timeout;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
