package io.shopr.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryRequest {
    private String name;

    private CategoryRequest(){

    }
}
