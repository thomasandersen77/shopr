package io.shopr.shopr.controllers;

import io.shopr.shopr.entities.Product;
import io.shopr.shopr.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductRepository repository;

    @Autowired
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping("product")
    public Long createProduct(@RequestBody Product product) {
        product = repository.save(product);
        log.info("Created Product. Id = {}, Name = {}, category = {}",
                product.getId(),
                product.getName(),
                product.getCategory().getName());
        return product.getId();
    }

    @GetMapping("product")
    public ProductList getProductList() {
        return new ProductList(repository.findAll());
    }

    @GetMapping("product/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No product with id: " + id));
        return product;
    }
}
