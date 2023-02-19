package pl.lodz.uni.edu.gin.requests.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public record CategoryUpdateRequest(
        String name,
        String description
) {
    public CategoryUpdateRequest {
//        if (name != null && name.isEmpty()) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "Name cannot be empty"
//            );
//        }
//
//        if (description != null && description.isEmpty()) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "Description cannot be empty"
//            );
//        }
    }
}
