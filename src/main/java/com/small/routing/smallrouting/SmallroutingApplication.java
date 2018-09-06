package com.small.routing.smallrouting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.small.routing.smallrouting")
@MapperScan(basePackages = "com.small.routing.smallrouting.mapper")
@EnableCaching
public class SmallroutingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmallroutingApplication.class, args);
    }
}
