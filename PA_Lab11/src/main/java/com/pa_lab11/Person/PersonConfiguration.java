package com.pa_lab11.Person;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PersonConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(PersonRepository repository) {
        return args -> {
            Person p1 = new Person("BeanPerson1");
            Person p2 = new Person("BeanPerson2");
            repository.saveAll(List.of(p1, p2));
        };
    }
}
