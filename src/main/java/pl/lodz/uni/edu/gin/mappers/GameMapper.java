package pl.lodz.uni.edu.gin.mappers;

import org.mapstruct.Mapper;
import pl.lodz.uni.edu.gin.dto.GameDto;
import pl.lodz.uni.edu.gin.entities.Category;
import pl.lodz.uni.edu.gin.entities.Game;

@Mapper(componentModel = "spring")
public interface GameMapper {
    default String categoryToCategoryName(Category category) {
        return category.getName();
    }
    GameDto gameToDto(Game game);
}
