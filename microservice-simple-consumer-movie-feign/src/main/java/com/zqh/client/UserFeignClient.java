package com.zqh.client;

import com.zqh.entity.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import feign.Headers;

/**
 * @discription:
 * @date: 2019/02/22 下午4:40
 */
@FeignClient(name = "microservice-simple-provider-user")//, fallback = UserFeignClient.UserFeignClientFallback.class)
public interface UserFeignClient {

    @GetMapping("/users/{id}")
    User findById(@PathVariable("id") Long id);

    @PostMapping(value = "/users/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void upload(@RequestParam("type") String type, @RequestPart("file") MultipartFile file);

    @Component
    class UserFeignClientFallback implements UserFeignClient {
        @Override
        public User findById(Long id) {
            return new User();
        }

        @Override
        public void upload(String type, MultipartFile file) {
            throw new RuntimeException("调用失败");
        }
    }
}
