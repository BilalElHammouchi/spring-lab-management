package com.example.LabManagementApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class LabManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabManagementApplication.class, args);
    }
}
