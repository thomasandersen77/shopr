package io.shopr.controllers;

import io.shopr.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private List<Product> products = new ArrayList<>();

    private ProductList(){/* jackson */}

    public ProductList(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
