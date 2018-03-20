package com.jwebidai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by wjh on 2018/2/26.
 * 标注服务注册到eureka中
 * 扫描mapper接口,添加至bean容器
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.itmuch.cloud.study.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
