package com.wheat.datavisual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class DataVisualApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataVisualApplication.class, args);
    }

}
