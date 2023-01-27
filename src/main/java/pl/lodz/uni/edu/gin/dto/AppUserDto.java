package pl.lodz.uni.edu.gin.dto;

import java.util.List;

public record AppUserDto(
        int id,
        String username,
        List<String> games
) {
}
