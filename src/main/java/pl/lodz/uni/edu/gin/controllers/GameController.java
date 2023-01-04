package pl.lodz.uni.edu.gin.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.uni.edu.gin.dto.GameDto;
import pl.lodz.uni.edu.gin.services.GameService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GameDto> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable int id) {
        return ResponseEntity.of(gameService.getGameById(id));
    }

    @GetMapping(params = "name")
    public ResponseEntity<GameDto> getGameByName(@RequestParam String name) {
        return ResponseEntity.of(gameService.getGameByName(name));
    }

    @GetMapping(params = "category")
    @ResponseStatus(HttpStatus.OK)
    public List<GameDto> getGamesByCategory(@RequestParam String category) {
        return gameService.getGamesByCategory(category);
    }
}
