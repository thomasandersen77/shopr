package io.shopr.cart;

import io.shopr.model.Cart;
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
        Cart cart = repository.getOne(id);
        return cart;
    }
}
