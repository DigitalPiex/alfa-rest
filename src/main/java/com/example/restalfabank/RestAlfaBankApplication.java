package com.example.restalfabank;

import com.example.restalfabank.service.impl.WriteDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class RestAlfaBankApplication implements ApplicationRunner {

    private final WriteDbService writeServices;

    public static void main(String[] args) {
        SpringApplication.run(RestAlfaBankApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        writeServices.loadDataFromXmlToDb(args.getSourceArgs());
    }
}
