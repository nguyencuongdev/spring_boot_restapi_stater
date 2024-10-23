package spring_boot_starter_restapi.spring_boot_restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_boot_starter_restapi.spring_boot_restapi.models.Product;
import spring_boot_starter_restapi.spring_boot_restapi.models.ResponseObject;
import spring_boot_starter_restapi.spring_boot_restapi.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {
    //    DI - dependency injection
    @Autowired
    private ProductRepository repository;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getProductAll() {
        List<Product> products = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        "ok",
                        "Query product list successfully",
                        products
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable Long id) {
        Optional<Product> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(
                                "ok",
                                "Query product successfully",
                                foundProduct
                        )
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(
                                "false",
                                "Cannot find product by id " + id,
                                null
                        )
                );
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createProduct(@RequestBody Product product) {
        List<Product> products = repository.findByName(product.getName());
        if (!products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject(
                            "false",
                            "Product with the same name already exists",
                            null
                    )
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        "ok",
                        "Create product successfully",
                        repository.save(product)
                )
        );
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Optional<Product> foundProduct = repository.findById(id);
        if (foundProduct.isPresent()) {
            Product product = foundProduct.get();
            product.setName(newProduct.getName());
            product.setProductYear(newProduct.getProductYear());
            product.setPrice(newProduct.getPrice());
            product.setUrl(newProduct.getUrl());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "ok",
                            "Update product successfully",
                            repository.save(product)
                    )
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(
                        "false",
                        "Cannot find product by id " + id,
                        null));

    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
       boolean exitProduct = repository.existsById(id);
       if(exitProduct) {
           repository.deleteById(id);
           return ResponseEntity.status(HttpStatus.OK).body(
                   new ResponseObject("true","Delete product successfully",null)
           );
       }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(
                        "false",
                        "Cannot find product want delete by id " + id,
                        null));
    }
}
