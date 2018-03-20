package com.jwebidai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by wjh on 2018/3/9.
 */
@Component
@PropertySource("kafka.properties")
@ConfigurationProperties(prefix = "poolconfig")
public class KafkaConfig {
    private Integer maxTotal;
    private Integer minIdle;
    private Integer maxIdle;
    private Integer maxWaitMillis;
    private Boolean testOnBorrow;
    private String syncProducerSerializer;
    private String syncProducerBrokers;

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(Integer maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public String getSyncProducerSerializer() {
        return syncProducerSerializer;
    }

    public void setSyncProducerSerializer(String syncProducerSerializer) {
        this.syncProducerSerializer = syncProducerSerializer;
    }

    public String getSyncProducerBrokers() {
        return syncProducerBrokers;
    }

    public void setSyncProducerBrokers(String syncProducerBrokers) {
        this.syncProducerBrokers = syncProducerBrokers;
    }
}
