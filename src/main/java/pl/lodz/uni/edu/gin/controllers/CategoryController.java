package pl.lodz.uni.edu.gin.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.uni.edu.gin.dto.CategoryDto;
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

    @PostMapping
    public ResponseEntity<CategoryDto> createNewCategory(@RequestBody CategoryDto newCategory) {
        CategoryDto createdCategory = categoryService.addNewCategory(newCategory);

        return ResponseEntity
                .created(URI.create("/category/" + createdCategory.id()))
                .body(createdCategory);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> changeDescription(@PathVariable int id, @RequestBody CategoryDto categoryDto) {
        categoryService.changeDescription(id, categoryDto.description());

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
