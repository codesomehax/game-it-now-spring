package pl.lodz.uni.edu.gin.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.lodz.uni.edu.gin.dto.GameDto;
import pl.lodz.uni.edu.gin.entities.Category;
import pl.lodz.uni.edu.gin.entities.Game;
import pl.lodz.uni.edu.gin.mappers.GameMapper;
import pl.lodz.uni.edu.gin.repositories.CategoryRepository;
import pl.lodz.uni.edu.gin.repositories.GameRepository;
import pl.lodz.uni.edu.gin.requests.game.GameAdditionRequest;
import pl.lodz.uni.edu.gin.requests.game.GamePatchRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final CategoryRepository categoryRepository;

    public Optional<GameDto> getGameById(int id) {
        return gameRepository.findById(id)
                .map(gameMapper::gameToDto);
    }

    public Optional<GameDto> getGameByName(String name) {
        return gameRepository.findByName(name)
                .map(gameMapper::gameToDto);
    }

    public List<GameDto> getGamesByCategory(String categoryName) {
        return gameRepository.findByCategoriesContaining(categoryName).stream()
                .map(gameMapper::gameToDto)
                .collect(Collectors.toList());
    }

    public List<GameDto> getAllGames() {
        return gameRepository.findAll().stream()
                .map(gameMapper::gameToDto)
                .collect(Collectors.toList());
    }

    public GameDto addNewGame(GameAdditionRequest gameAdditionRequest) {
        if (gameRepository.existsByName(gameAdditionRequest.name())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A game with given name already exists"
            );
        }

        List<Category> categoriesToAssign = categoryRepository.findAllByNameIn(gameAdditionRequest.categories());

        if (categoriesToAssign.size() != gameAdditionRequest.categories().size()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "At least one of the given categories does not exist"
            );
        }

        Game game = gameMapper.gameAdditionRequestToGame(gameAdditionRequest);
        game.setCategories(categoriesToAssign);

        return gameMapper.gameToDto(gameRepository.save(game));
    }

    @Transactional
    public void patchGame(int id, GamePatchRequest gamePatchRequest) {
        gameRepository
                .findById(id)
                .ifPresentOrElse(
                        game -> {
                            if (gamePatchRequest.description() != null)
                                game.setDescription(gamePatchRequest.description());
                            if (gamePatchRequest.price() != null)
                                game.setPrice(gamePatchRequest.price());

                            if (gamePatchRequest.categories() != null) {
                                List<Category> categoriesToAssign = categoryRepository.findAllByNameIn(gamePatchRequest.categories());

                                if (categoriesToAssign.size() != gamePatchRequest.categories().size()) {
                                    throw new ResponseStatusException(
                                            HttpStatus.BAD_REQUEST,
                                            "At least one of the given categories does not exist"
                                    );
                                }

                                game.setCategories(categoriesToAssign);
                            }
                        },
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "A game with given id does not exist"
                            );
                        }
                );
    }

    public void deleteGame(int id) {
        gameRepository
                .findById(id)
                .ifPresentOrElse(
                        gameRepository::delete,
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "A game with given id does not exist"
                            );
                        }
                );
    }
}
