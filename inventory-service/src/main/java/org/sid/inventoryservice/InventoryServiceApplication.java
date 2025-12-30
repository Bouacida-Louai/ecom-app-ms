package org.sid.inventoryservice;

import org.sid.inventoryservice.Repository.ProductRepository;
import org.sid.inventoryservice.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository,
    RepositoryRestConfiguration repositoryRestConfiguration
    ) {
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Product.class);
            productRepository.saveAll(
                    java.util.List.of(
                            org.sid.inventoryservice.entities.Product.builder().name("Computer").price(1200).quantity(12).build(),
                            org.sid.inventoryservice.entities.Product.builder().name("Printer").price(120).quantity(32).build(),
                            org.sid.inventoryservice.entities.Product.builder().name("Smartphone").price(900).quantity(31).build()
                    )
            );
            productRepository.findAll().forEach(System.out::println);
        };
    }

}
