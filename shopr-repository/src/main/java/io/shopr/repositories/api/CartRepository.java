package io.shopr.repositories.api;

import io.shopr.repositories.domain.Cart;
import io.shopr.repositories.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByCustomer(Customer customer);
}
