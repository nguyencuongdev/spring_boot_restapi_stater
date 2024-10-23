package spring_boot_starter_restapi.spring_boot_restapi.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_boot_starter_restapi.spring_boot_restapi.models.Product;
import spring_boot_starter_restapi.spring_boot_restapi.repositories.ProductRepository;


@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    public CommandLineRunner initDatabase(ProductRepository productRepository) {
        return (args) -> {
            Product productA = new Product("ProductA", 2024, 100.00, "");
            Product productB = new Product("ProductB", 2024, 90.00, "");
            logger.info("insert data: {}", productRepository.save(productA));
            logger.info("insert data: {}", productRepository.save(productB));
        };
    }
}
