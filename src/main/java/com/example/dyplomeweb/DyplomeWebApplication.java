package com.example.dyplomeweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DyplomeWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DyplomeWebApplication.class, args);
    }

}
