package com.smallow;

import com.smallow.service.WebSocket;
import com.smallow.service.WebSocket2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan({"com.smallow.mapper","com.smallow.workflow.example.qingjia","com.smallow.workflow.mapper"})
public class BadmintonBarApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext=SpringApplication.run(BadmintonBarApplication.class, args);
        WebSocket.setApplicationContext(applicationContext);
        WebSocket2.setApplicationContext(applicationContext);
    }
}


//@SpringBootApplication注解中已经包含了@ComponentScan和@EnableConfigurationProperties注解。