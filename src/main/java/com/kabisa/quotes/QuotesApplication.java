package com.kabisa.quotes;

import com.kabisa.quotes.repositories.Repository;
import com.kabisa.quotes.services.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuotesApplication {

    public static void main(String[] args) {
        Repository.initialize(Service.Api);
        SpringApplication.run(QuotesApplication.class, args);
    }
}
