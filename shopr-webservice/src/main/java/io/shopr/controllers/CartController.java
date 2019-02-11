package io.shopr.controllers;

import io.shopr.repositories.api.CartRepository;
import io.shopr.repositories.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    private final CartRepository repository;

    @Autowired
    public CartController(CartRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{id}")
    public Cart getCart(@PathVariable("id") Long id) {
        return repository.getOne(id);
    }
}
