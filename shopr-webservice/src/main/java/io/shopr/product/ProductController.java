package io.shopr.product;

import io.shopr.product.dto.ProductListDto;
import io.shopr.repositories.api.ProductRepository;
import io.shopr.repositories.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Long createProduct(@RequestBody Product productReq) {
        var product = repository.save(productReq);
        if (product.getId() == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not create Product with name: " + product.getName());
        }

        log.info("Created Product. Id = {}, Name = {}, category = {}",
                product.getId(),
                product.getName(),
                product.getCategory().getName());
        return product.getId();
    }

    @GetMapping("product")
    public ProductListDto getProductList() {
        var products = repository.findAll();
        if (products == null || products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could find any products");
        }
        return new ProductListDto(products);
    }

    @GetMapping("product/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        final var product = repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No product with id: " + id));
        log.info("Found Product {} with ID = {}", product.getName(), id);
        return product;
    }
}
