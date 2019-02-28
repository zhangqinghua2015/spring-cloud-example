package com.zqh.controller;

import com.zqh.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @discription:
 * @date: 2019/02/22 下午2:20
 */
@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/users/{id}")
    public User findById(@PathVariable Long id) {
        User user = this.restTemplate.getForObject("http://microservice-simple-provider-user/users/{id}", User.class, id);
        // 电影微服务业务
        return user;
    }

}
