package com.zqh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @discription:
 * @date: 2019/02/22 下午2:23
 */
@SpringBootApplication
@EnableConfigServer
public class LocalConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalConfigServerApplication.class, args);
    }
}
