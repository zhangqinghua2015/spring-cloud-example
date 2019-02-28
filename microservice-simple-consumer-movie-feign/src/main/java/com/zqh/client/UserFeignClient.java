package com.zqh.client;

import com.zqh.entity.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @discription:
 * @date: 2019/02/22 下午4:40
 */
@FeignClient(name = "microservice-simple-provider-user", fallback = UserFeignClient.UserFeignClientFallback.class)
public interface UserFeignClient {

    @GetMapping("/users/{id}")
    User findById(@PathVariable("id") Long id);

    @Component
    class UserFeignClientFallback implements UserFeignClient {
        @Override
        public User findById(Long id) {
            return new User();
        }
    }
}
