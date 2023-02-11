package pl.lodz.uni.edu.gin.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.uni.edu.gin.dto.GameDto;
import pl.lodz.uni.edu.gin.services.CartService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{id}")
    public List<GameDto> getCartById(@PathVariable int id) {
        return cartService.getCartById(id);
    }

    @PostMapping(value = "/{cartId}", params = "gameId")
    public ResponseEntity<Void> addGameToCartById(@PathVariable int cartId, @RequestParam int gameId) {
        cartService.addGameToCartById(cartId, gameId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping(value = "/{cartId}", params = "gameId")
    public ResponseEntity<Void> deleteGameFromCartById(@PathVariable int cartId, @RequestParam int gameId) {
        cartService.deleteGameFromCartById(cartId, gameId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> clearCartById(@PathVariable int id) {
        cartService.clearCartById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/{id}/buy")
    public List<GameDto> buyGamesById(@PathVariable int id) {
        return cartService.buyGameById(id);
    }
}
