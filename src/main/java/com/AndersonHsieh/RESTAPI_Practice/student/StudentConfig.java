package com.AndersonHsieh.RESTAPI_Practice.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
//indicates that this is a configuration file
//Spring reads this configuration file then creates instance of StudentRepository(dependency injection)
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student Andy = new Student(
                    "Andy",
                    "andyhsieh@gmail.com",
                    LocalDate.of(2000, Month.MARCH,30)
            );

            Student Jill = new Student(
                    "Jill",
                    "jillniu@gmail.com",
                    LocalDate.of(2000, Month.MARCH,30)
            );

            repository.saveAll(
                    List.of(Andy,Jill)
            );
        };
    }


}
