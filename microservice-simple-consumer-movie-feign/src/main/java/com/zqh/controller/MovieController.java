package com.zqh.controller;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zqh.client.UserFeignClient;
import com.zqh.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @discription:
 * @date: 2019/02/22 下午2:20
 */
@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    private UserFeignClient userFeighClient;

//    @HystrixCommand(fallbackMethod = "findByIdFallback")
    @GetMapping("/users/{id}")
    public User findById(@PathVariable Long id) {
        User user = userFeighClient.findById(id);
        // 电影微服务业务
        return user;
    }

    @PostMapping("/upload")
    public void upload(@RequestParam("type") String type, @RequestPart("file") MultipartFile file) {
        userFeighClient.upload(type, file);
    }

    public User findByIdFallback(Long id) {
        return new User();
    }

}
