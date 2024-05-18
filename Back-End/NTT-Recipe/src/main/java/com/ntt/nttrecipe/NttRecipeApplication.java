package com.ntt.nttrecipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NttRecipeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NttRecipeApplication.class, args);
        System.out.println("Swagger : http://localhost:8101/swagger-ui/index.html");

    }

}
