package com.julespi.springbootapirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class SpringBootApirestApplication {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);    //changes the default language
        SpringApplication.run(SpringBootApirestApplication.class, args);
    }

}
