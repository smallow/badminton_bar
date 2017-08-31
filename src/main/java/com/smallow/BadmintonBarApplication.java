package com.smallow;

import com.smallow.service.WebSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BadmintonBarApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext=SpringApplication.run(BadmintonBarApplication.class, args);
        WebSocket.setApplicationContext(applicationContext);
    }
}
