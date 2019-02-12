package io.shopr.controllers;

import io.shopr.dto.CategoryRequestDto;
import io.shopr.repositories.api.CategoryRepository;
import io.shopr.repositories.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequestDto request) {
        try {
            Category category = categoryRepository.save(new Category(request.getName()));
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("category/{id}")
    public Category getCategory(@PathVariable("id") Long id){
        return categoryRepository.findById(id).orElseGet(() -> new Category("lazy"));
    }

    @GetMapping("category")
    public List<Category> getCategory(){
        return new ArrayList<>(categoryRepository.findAll());
    }
}
