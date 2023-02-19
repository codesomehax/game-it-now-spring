package pl.lodz.uni.edu.gin.requests.game;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public record GamePatchRequest(
        String name,
        String description,
        Double price,
        String imageUrl,
        List<String> categories
) {
    public GamePatchRequest {
//        if (description != null && description.isEmpty()) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "Description cannot be empty"
//            );
//        }
//
//        if (imageUrl != null && imageUrl.isEmpty()) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "ImageUrl cannot be empty"
//            );
//        }
//
//        if (price != null && price < 0) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "Price cannot be lower than 0"
//            );
//        }
    }
}
