package com.bae.spb.mailsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
public class MailSenderApplication {


    public static void main(String[] args) {
        SpringApplication.run(MailSenderApplication.class, args);
    }


}