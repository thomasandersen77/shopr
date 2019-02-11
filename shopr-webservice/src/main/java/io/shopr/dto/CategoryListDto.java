package io.shopr.dto;

import io.shopr.repositories.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryListDto {
    private List<Category> list = new ArrayList<>();

    private CategoryListDto(){

    }

    public CategoryListDto(List<Category> list) {
        this.list = list;
    }

    public List<Category> getList() {
        return list;
    }
}

