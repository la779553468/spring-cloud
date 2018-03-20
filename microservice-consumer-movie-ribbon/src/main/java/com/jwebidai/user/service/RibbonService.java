package com.jwebidai.user.service;


import com.jwebidai.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wjh on 2018/2/28.
 */
@Service
public class RibbonService {
    @Autowired
    private RestTemplate restTemplate;

    public User findById(Integer id) {
        return restTemplate.getForObject("http://microservice-provider-user/"+id,User.class);
    }
}
