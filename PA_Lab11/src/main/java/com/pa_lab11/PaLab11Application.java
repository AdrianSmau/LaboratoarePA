package com.pa_lab11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PaLab11Application {

    public static void main(String[] args) {
        SpringApplication.run(PaLab11Application.class, args);
    }

}
