package io.shopr.category;

import io.shopr.category.dto.CategoryRequestDto;
import io.shopr.repositories.api.CategoryRepository;
import io.shopr.repositories.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
}
