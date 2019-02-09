package io.shopr.product.exceptions;

import io.shopr.common.ShoprException;

public class CreateProductExcepion extends ShoprException {
    public CreateProductExcepion(String message, Type type) {
        super(message, type);
    }
}
