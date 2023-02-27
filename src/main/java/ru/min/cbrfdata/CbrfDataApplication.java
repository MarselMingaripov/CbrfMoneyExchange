package ru.min.cbrfdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.min.cbrfdata.service.JsonService;

@SpringBootApplication
public class CbrfDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(CbrfDataApplication.class, args);

    }
}
