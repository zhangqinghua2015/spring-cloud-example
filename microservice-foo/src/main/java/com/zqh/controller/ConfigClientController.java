package com.zqh.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @discription:
 * @date: 2019/02/28 下午3:07
 */
@RestController
@RefreshScope
public class ConfigClientController {

    private ApplicationContext context;

    @Value("${profile}")
    private String profile;

    @GetMapping("/profile")
    public String hello() {
        return this.profile + "======" + this;
    }

//    @Value("${spring.datasource.password}")
//    private String password;
//
//    @GetMapping("/password")
//    public String password() {
//        return this.password;
//    }

}
