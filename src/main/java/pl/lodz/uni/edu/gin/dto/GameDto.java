package pl.lodz.uni.edu.gin.dto;

import java.util.List;

public record GameDto(
        int id,
        String name,
        String description,
        double price,
        String imageUrl,
        List<String> categories
) {
}
