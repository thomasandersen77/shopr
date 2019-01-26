package io.shopr.controllers;

import io.shopr.entities.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private List<ProductItem> productItems = new ArrayList<>();

    private ProductList(){/* jackson */}

    public ProductList(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }
}
