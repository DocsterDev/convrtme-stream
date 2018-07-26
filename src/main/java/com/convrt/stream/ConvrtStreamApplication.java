package com.convrt.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableCaching
public class ConvrtStreamApplication {

    @Autowired
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        System.getProperties().put("spring.http.multipart.max-file-size", "-1");
        System.getProperties().put("spring.http.multipart.max-request-size", "-1");
        SpringApplication.run(ConvrtStreamApplication.class, args);
    }

    @PostConstruct
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

}
