package io.shopr.dto;

import javax.validation.constraints.NotBlank;

public class CategoryRequestDto {
    @NotBlank
    private String name;

    private CategoryRequestDto() {
    }

    public CategoryRequestDto(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CategoryRequestDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
