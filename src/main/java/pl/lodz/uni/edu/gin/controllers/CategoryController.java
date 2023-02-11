package pl.lodz.uni.edu.gin.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.uni.edu.gin.dto.CategoryDto;
import pl.lodz.uni.edu.gin.requests.category.CategoryAdditionRequest;
import pl.lodz.uni.edu.gin.requests.category.CategoryUpdateRequest;
import pl.lodz.uni.edu.gin.services.CategoryService;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int id) {
        return ResponseEntity
                .of(categoryService.getCategoryById(id));
    }

    @GetMapping(params = "name")
    public ResponseEntity<CategoryDto> getCategoryByName(@RequestParam String name) {
        return ResponseEntity
                .of(categoryService.getCategoryByName(name));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> addNewCategory(@RequestBody CategoryAdditionRequest categoryAdditionRequest) {
        CategoryDto createdCategory = categoryService.addNewCategory(categoryAdditionRequest);

        return ResponseEntity
                .created(URI.create("/category/" + createdCategory.id()))
                .body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable int id, @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        categoryService.updateCategory(id, categoryUpdateRequest);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
