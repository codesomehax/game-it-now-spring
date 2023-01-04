package pl.lodz.uni.edu.gin.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.uni.edu.gin.dto.CategoryDto;
import pl.lodz.uni.edu.gin.mappers.CategoryMapper;
import pl.lodz.uni.edu.gin.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToDto)
                .toList();
    }

    public Optional<CategoryDto> getCategoryById(int id) {
        return categoryRepository.findById(id).map(categoryMapper::categoryToDto);
    }
}
