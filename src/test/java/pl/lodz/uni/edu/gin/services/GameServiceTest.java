package pl.lodz.uni.edu.gin.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.lodz.uni.edu.gin.Stubs;
import pl.lodz.uni.edu.gin.entities.Game;
import pl.lodz.uni.edu.gin.repositories.GameRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class GameServiceTest extends Stubs {
//    @Autowired
//    private GameService gameService;
//    @MockBean
//    private GameRepository gameRepository;
//
//    @Before
//    public void setUp() {
//        when(gameRepository.findByName("The Witcher"))
//                .thenReturn(Optional.of(witcher));
//    }
//
//    @Test
//    public void whenGetGameByName_thenReturnGame() {
//        // when
//        Optional<Game> found = gameService.getGameByName("The Witcher").map;
//
//        // then
//        Game game = found.orElseThrow();
//
//        assertThat(game)
//                .usingRecursiveComparison()
//                .ignoringFields("id", "categories")
//                .isEqualTo(witcher);
//    }
}
