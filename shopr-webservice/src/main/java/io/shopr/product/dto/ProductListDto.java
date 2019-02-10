package io.shopr.product.dto;

import io.shopr.repositories.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class ProductListDto {
    private List<Product> products;

    protected ProductListDto(){
        this.products = new ArrayList<>();
    }

    public ProductListDto(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
