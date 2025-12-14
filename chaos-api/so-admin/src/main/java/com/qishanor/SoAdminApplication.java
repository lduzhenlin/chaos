package com.qishanor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.qishanor.system.mapper")
@SpringBootApplication
public class SoAdminApplication {

    public static void main(String[] args) {

        SpringApplication.run(SoAdminApplication.class, args);
//        String password = "123456";
//        String hashedPassword = BCrypt.hashpw(password);
//        System.out.println(hashedPassword);
    }

}
