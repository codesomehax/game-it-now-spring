package pl.lodz.uni.edu.gin.requests.game;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

public record GameAdditionRequest(
        String name,
        String description,
        Double price,
        String imageUrl,
        List<String> categories
) {
    public GameAdditionRequest {
        Objects.requireNonNull(name, "A name is required for game addition operation");
        Objects.requireNonNull(description, "A description is required for game addition operation");
        Objects.requireNonNull(price, "A price is required for game addition operation");
        Objects.requireNonNull(imageUrl, "An imageUrl is required for game addition operation");
        Objects.requireNonNull(categories, "Categories are required for game addition operation");

        if (name.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Name cannot be empty"
            );
        }

        if (description.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Description cannot be empty"
            );
        }

        if (imageUrl.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "ImageUrl cannot be empty"
            );
        }

        if (price < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Price cannot be lower than 0"
            );
        }
    }
}
