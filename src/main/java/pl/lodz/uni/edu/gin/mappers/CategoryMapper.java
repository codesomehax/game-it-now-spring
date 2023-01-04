package pl.lodz.uni.edu.gin.mappers;

import org.mapstruct.Mapper;
import pl.lodz.uni.edu.gin.dto.CategoryDto;
import pl.lodz.uni.edu.gin.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto categoryToDto(Category category);
}
