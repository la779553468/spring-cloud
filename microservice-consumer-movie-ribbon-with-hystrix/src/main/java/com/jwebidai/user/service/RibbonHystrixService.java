package com.jwebidai.user.service;


import com.jwebidai.user.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wjh on 2018/2/28.
 */
@Service
public class RibbonHystrixService {
    @Autowired
    private RestTemplate restTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(RibbonHystrixService.class);

    /**
     * 使用@HystrixCommand注解指定当该方法发生异常时调用的方法
     * @param id
     * @return 通过id查询到的用户
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public User findById(Integer id) {
        return restTemplate.getForObject("http://microservice-provider-user/"+id,User.class);
    }

    /**
     * hystrix fallback方法
     * @param id
     * @return 默认的用户
     */
    public User fallback(Integer id){
        RibbonHystrixService.LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
        User user = new User();
        user.setId(-1);
        user.setUsername("default username");
        user.setAge("0");
        return user;
    }
}