package io.shopr.product.exceptions;

import io.shopr.common.ShoprException;

public class ProductNotFoundException extends ShoprException {
    public ProductNotFoundException(String message, Type type) {
        super(message, type);
    }
}
