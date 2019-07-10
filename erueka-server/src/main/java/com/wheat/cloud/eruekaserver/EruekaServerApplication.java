package com.wheat.cloud.eruekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// 服务发现 服务端注解
@EnableEurekaServer
// springboot启动主程序注解
@SpringBootApplication
public class EruekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EruekaServerApplication.class, args);
    }

}
