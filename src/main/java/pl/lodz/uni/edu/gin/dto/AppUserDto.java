package pl.lodz.uni.edu.gin.dto;

import pl.lodz.uni.edu.gin.entities.AppUser;

import java.util.List;

public record AppUserDto(
        int id,
        String username,
        String email,
        AppUser.Role role,
        List<String> games
) {
}
