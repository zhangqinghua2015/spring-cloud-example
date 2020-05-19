package com.zqh;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

/**
 * @discription:
 * @date: 2019/02/22 下午2:23
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
public class ConsumerMovieFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerMovieFeignApplication.class, args);
    }

    /*@Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }*/

    @Bean
    public Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                System.out.println("调用interceptor");
                Map<String, String> headers = getHeaders(getHttpServletRequest());
                for (String headerName : headers.keySet()) {
                    requestTemplate.header(headerName, getHeaders(getHttpServletRequest()).get(headerName));
                }
            }

            private HttpServletRequest getHttpServletRequest() {
                try {
                    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            private Map<String, String> getHeaders(HttpServletRequest request) {
                Map<String, String> map = new LinkedHashMap<>();
                Enumeration<String> enumeration = request.getHeaderNames();
                while (enumeration.hasMoreElements()) {
                    String key = enumeration.nextElement();
                    String value = request.getHeader(key);
                    map.put(key, value);
                }
                return map;
            }
        };
        return requestInterceptor;
    }

    /*@Bean
    public RequestInterceptor requestInterceptor() {
        RequestInterceptor requestInterceptor = (requestTemplate) -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletRequest request = attributes.getRequest(); // 因为开启了feign熔断器，会空指针 feign: hystrix: enabled: false # 开启Feign的熔断功能
            Enumeration<String> headerNames = request.getHeaderNames();
            System.out.println("调用interceptor");
            try {
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        if (name.equals("userId".toLowerCase()) ||
                                name.equals("userGid".toLowerCase()) ||
                                name.equals("userMobile".toLowerCase())) {
                            String values = request.getHeader(name);
                                requestTemplate.header(name, values);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        return requestInterceptor;
    }*/

}
