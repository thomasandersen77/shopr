package io.shopr.shopr.controllers;

import io.shopr.shopr.entities.Product;
import io.shopr.shopr.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Product> getProductList() {
        return repository.findAll();
    }

    @GetMapping("product/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return repository.getOne(id);
    }
}
