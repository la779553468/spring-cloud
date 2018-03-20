package com.jwebidai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by wjh on 2018/2/26.
 * 标注服务注册到eureka中
 * 扫描mapper接口,添加至bean容器
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SmsApplication {
    public static ConfigurableApplicationContext ac;
    public static void main(String[] args) {
        ac = SpringApplication.run(SmsApplication.class, args);
    }
}
