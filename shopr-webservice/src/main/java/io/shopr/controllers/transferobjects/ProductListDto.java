package io.shopr.controllers.transferobjects;

import io.shopr.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListDto {
    private List<Product> products = new ArrayList<>();

    private ProductListDto(){/* jackson */}

    public ProductListDto(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
