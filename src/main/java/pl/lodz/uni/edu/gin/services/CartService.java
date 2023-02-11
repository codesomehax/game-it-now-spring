package pl.lodz.uni.edu.gin.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.lodz.uni.edu.gin.dto.GameDto;
import pl.lodz.uni.edu.gin.entities.AppUser;
import pl.lodz.uni.edu.gin.entities.Game;
import pl.lodz.uni.edu.gin.mappers.GameMapper;
import pl.lodz.uni.edu.gin.repositories.AppUserRepository;
import pl.lodz.uni.edu.gin.repositories.GameRepository;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private final AppUserRepository appUserRepository;
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public List<GameDto> getCartById(int id) {
        return appUserRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "A user with given id does not exist"
                    );
                }).getCart()
                .stream()
                .map(gameMapper::gameToDto)
                .toList();
    }

    @Transactional
    public void addGameToCartById(int cartId, int gameId) {
        AppUser appUser = appUserRepository
                .findById(cartId)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "A user with given id does not exist"
                    );
                });

        if (appUser
                .getCart()
                .stream()
                .anyMatch(game -> game.getId() == gameId)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "This game is already in the cart"
            );
        }

        if (appUser
                .getGames()
                .stream()
                .anyMatch(game -> game.getId() == gameId)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "This game is already owned by the user"
            );
        }

        Game gameToAdd = gameRepository
                .findById(gameId)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "A game with given id does not exist"
                    );
                });

        appUser.getCart().add(gameToAdd);
    }

    @Transactional
    public void deleteGameFromCartById(int cartId, int gameId) {
        List<Game> cart = appUserRepository
                .findById(cartId)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "A user with given id does not exist"
                    );
                }).getCart();

        cart
                .stream()
                .filter(game -> game.getId() == gameId)
                .findFirst()
                .ifPresentOrElse(
                        cart::remove,
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "A cart with given id does not contain a game with given id"
                            );
                        }
                );
    }

    @Transactional
    public void clearCartById(int id) {
        appUserRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "A user with given id does not exist"
                    );
                }).setCart(Collections.emptyList());
    }

    @Transactional
    public List<GameDto> buyGameById(int id) {
        AppUser appUser = appUserRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "A user with given id does not exist"
                    );
                });

        if (appUser.getCart().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The cart with given id is empty"
            );
        }

        appUser.getGames().addAll(appUser.getCart());
        appUser.setCart(Collections.emptyList());

        return appUser
                .getGames()
                .stream()
                .map(gameMapper::gameToDto)
                .toList();
    }
}
