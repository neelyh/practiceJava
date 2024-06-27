package com.example.demo;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Salesperson;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.SalespersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository customerRepository, SalespersonRepository salespersonRepository) {
        return (args) -> {
            // Insert Customers
            Customer customer1 = new Customer();
            customer1.setName("John Doe");
            customer1.setEmail("john.doe@example.com");
            customerRepository.save(customer1);

            Customer customer2 = new Customer();
            customer2.setName("Jane Smith");
            customer2.setEmail("jane.smith@example.com");
            customerRepository.save(customer2);

            // Insert Salespersons
            Salesperson salesperson1 = new Salesperson();
            salesperson1.setName("Phil Johnson");
            salesperson1.setEmail("phil.johnson@imanage.com");
            salespersonRepository.save(salesperson1);

            Salesperson salesperson2 = new Salesperson();
            salesperson2.setName("Mark Brown");
            salesperson2.setEmail("mark.brown@imanage.com");
            salespersonRepository.save(salesperson2);
        };
    }
}
