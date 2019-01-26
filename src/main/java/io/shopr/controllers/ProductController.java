package io.shopr.controllers;

import io.shopr.entities.ProductItem;
import io.shopr.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductRepository repository;

    @Autowired
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping("product")
    public Long createProduct(@RequestBody ProductItem productItem) {
        productItem = repository.save(productItem);
        log.info("Created ProductItem. Id = {}, Name = {}, category = {}",
                productItem.getId(),
                productItem.getName(),
                productItem.getCategory().getName());
        return productItem.getId();
    }

    @GetMapping("product")
    public ProductList getProductList() {
        return new ProductList(repository.findAll());
    }

    @GetMapping("product/{id}")
    public ProductItem getProductById(@PathVariable("id") Long id) {
        ProductItem productItem = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No productItem with id: " + id));
        return productItem;
    }
}
