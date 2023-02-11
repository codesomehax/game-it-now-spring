package pl.lodz.uni.edu.gin.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.lodz.uni.edu.gin.dto.GameDto;
import pl.lodz.uni.edu.gin.entities.Category;
import pl.lodz.uni.edu.gin.entities.Game;
import pl.lodz.uni.edu.gin.requests.game.GameAdditionRequest;

@Mapper(componentModel = "spring")
public interface GameMapper {
    default String categoryToCategoryName(Category category) {
        return category.getName();
    }
    GameDto gameToDto(Game game);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Game gameAdditionRequestToGame(GameAdditionRequest gameAdditionRequest);
}
