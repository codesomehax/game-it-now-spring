package pl.lodz.uni.edu.gin.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.lodz.uni.edu.gin.dto.CategoryDto;
import pl.lodz.uni.edu.gin.entities.Category;
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

    public CategoryDto addNewCategory(CategoryDto newCategoryDto) {
        if (categoryRepository.findByName(newCategoryDto.name()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A category with given name already exists"
            );
        }
        Category category = categoryMapper.dtoToCategory(newCategoryDto);

        return categoryMapper.categoryToDto(categoryRepository.save(category));
    }

    @Transactional
    public void changeDescription(int id, String description) {
        categoryRepository
                .findById(id)
                .ifPresentOrElse(
                        category -> category.setDescription(description),
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "A category with given id does not exist"
                            );
                        }
                );
    }

    public void deleteCategory(int id) {
        categoryRepository
                .findById(id)
                .ifPresentOrElse(
                        categoryRepository::delete,
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "A category with given id does not exist"
                            );
                        }
                );
    }
}
