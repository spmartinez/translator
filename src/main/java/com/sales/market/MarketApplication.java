package com.sales.market;

import com.sales.market.mail.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class MarketApplication {


    public static void main(String[] args) {

        SpringApplication.run(MarketApplication.class, args);
    }

}
