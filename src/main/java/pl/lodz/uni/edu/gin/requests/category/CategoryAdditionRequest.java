package pl.lodz.uni.edu.gin.requests.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public record CategoryAdditionRequest(
        String name,
        String description
) {
    public CategoryAdditionRequest {
        Objects.requireNonNull(name, "A name is required for category addition operation");
        Objects.requireNonNull(description, "A description is required for category addition operation");

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
    }
}
