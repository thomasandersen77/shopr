package io.shopr.service;

import io.shopr.repositories.api.CartRepository;
import io.shopr.repositories.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateCartPriceService {
    private CartRepository cartRepository;

    @Autowired
    public CalculateCartPriceService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public double getCartTotal(Customer customer) {
        return cartRepository.findByCustomer(customer)
                .getProducts().stream()
                .map((var p) -> p.getPrice() * p.getNumberOfItems())
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
