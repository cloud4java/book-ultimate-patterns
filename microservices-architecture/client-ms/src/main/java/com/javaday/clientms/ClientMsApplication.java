package com.javaday.clientms;

import com.javaday.clientms.controller.ClientController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ClientController.class})
public class ClientMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientMsApplication.class, args);
    }

}
