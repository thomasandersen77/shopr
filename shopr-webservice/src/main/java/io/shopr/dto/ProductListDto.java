package io.shopr.dto;

import io.shopr.repositories.domain.Product;

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
