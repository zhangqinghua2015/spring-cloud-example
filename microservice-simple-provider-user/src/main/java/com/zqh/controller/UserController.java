package com.zqh.controller;

import com.zqh.entity.User;
import com.zqh.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Enumeration;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;


/**
 * @discription:
 * @date: 2019/02/22 上午10:36
 */
@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        return this.userRepository.findById(id);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@RequestParam("type") String type, @RequestPart("file") MultipartFile file) {
        /*Enumeration<String> headerNames = request.getHeaderNames();
        if (null != headerNames) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                System.out.println(headerName + "==" + request.getHeader(headerName));
            }
        }*/
        System.out.println("type==" + type);
        System.out.println("file==" +  file);
        System.out.println("fileName==" + file.getName());
        System.out.println("fileSize==" + file.getSize());
    }
}
