package io.shopr.product.dto;

import io.shopr.repositories.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDto {
    private List<Product> products = new ArrayList<>();
}
