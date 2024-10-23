package spring_boot_starter_restapi.spring_boot_restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot_starter_restapi.spring_boot_restapi.models.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByName(String name);
}
