package pl.lodz.uni.edu.gin.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.uni.edu.gin.dto.GameDto;
import pl.lodz.uni.edu.gin.requests.game.GameAdditionRequest;
import pl.lodz.uni.edu.gin.requests.game.GamePatchRequest;
import pl.lodz.uni.edu.gin.services.GameService;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    @GetMapping
    public List<GameDto> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable int id) {
        return ResponseEntity
                .of(gameService.getGameById(id));
    }

    @GetMapping(params = "name")
    public ResponseEntity<GameDto> getGameByName(@RequestParam String name) {
        return ResponseEntity
                .of(gameService.getGameByName(name));
    }

    @GetMapping(params = "category")
    public List<GameDto> getGamesByCategory(@RequestParam String category) {
        return gameService.getGamesByCategory(category);
    }

    @PostMapping
    public ResponseEntity<GameDto> addNewGame(@RequestBody GameAdditionRequest gameAdditionRequest) {
        GameDto addedGame = gameService.addNewGame(gameAdditionRequest);

        return ResponseEntity
                .created(URI.create("/game/" + addedGame.id()))
                .body(addedGame);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchGame(@PathVariable int id, @RequestBody GamePatchRequest gamePatchRequest) {
        gameService.patchGame(id, gamePatchRequest);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable int id) {
        gameService.deleteGame(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
