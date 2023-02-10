package pl.lodz.uni.edu.gin.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.lodz.uni.edu.gin.dto.CategoryDto;
import pl.lodz.uni.edu.gin.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto categoryToDto(Category category);
    @Mapping(target = "gamesOfCategory", ignore = true)
    Category dtoToCategory(CategoryDto categoryDto);
}
