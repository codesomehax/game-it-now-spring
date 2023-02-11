package pl.lodz.uni.edu.gin.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.lodz.uni.edu.gin.dto.CategoryDto;
import pl.lodz.uni.edu.gin.entities.Category;
import pl.lodz.uni.edu.gin.requests.category.CategoryAdditionRequest;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto categoryToDto(Category category);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "gamesOfCategory", ignore = true)
    Category categoryAdditionRequestToCategory(CategoryAdditionRequest categoryAdditionRequest);
}
