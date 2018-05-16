package com.beifeng.hadoop.spring.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class CloudZuul {
    public static void main(String[] args) {
        SpringApplication.run(CloudZuul.class, args);
    }
}
