package com.jwebidai.user.controller;

import com.jwebidai.user.entity.User;
import com.jwebidai.user.service.RibbonHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wjh on 2018/2/28.
 */
@RestController
public class RibbonHystrixController {
    @Autowired
    private RibbonHystrixService ribbonHystrixService;

    @GetMapping("/ribbonHystrix/{id}")
    public User findById(@PathVariable Integer id){
        return ribbonHystrixService.findById(id);
    }
}
