package io.shopr.cart;

import io.shopr.repositories.api.CartRepository;
import io.shopr.repositories.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class CartController {
    private final CartRepository repository;

    @Autowired
    public CartController(CartRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Cart getCart(@PathParam("{id}") Long id) {
        return repository.getOne(id);
    }
}
