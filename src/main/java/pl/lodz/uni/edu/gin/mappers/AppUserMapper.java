package pl.lodz.uni.edu.gin.mappers;

import org.mapstruct.Mapper;
import pl.lodz.uni.edu.gin.dto.AppUserDto;
import pl.lodz.uni.edu.gin.entities.AppUser;
import pl.lodz.uni.edu.gin.entities.Game;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    default String gameToGameName(Game game) {
        return game.getName();
    }
    AppUserDto appUserToDto(AppUser appUser);
}
