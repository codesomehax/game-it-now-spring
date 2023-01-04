package pl.lodz.uni.edu.gin.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.uni.edu.gin.dto.GameDto;
import pl.lodz.uni.edu.gin.mappers.GameMapper;
import pl.lodz.uni.edu.gin.repositories.GameRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

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
}
