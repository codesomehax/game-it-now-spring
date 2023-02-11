package pl.lodz.uni.edu.gin.requests.cart;

import java.util.Objects;

public record CartGameDeletionRequest(
        Integer gameId
) {
    public CartGameDeletionRequest {
        Objects.requireNonNull(gameId, "A game id is required for cart game addition operation");
    }
}
