package pl.lodz.uni.edu.gin.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.lodz.uni.edu.gin.entities.Game;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
public class GameRepositoryTest extends StubsManager {
    @Autowired
    private GameRepository gameRepository;

    @Test
    public void whenFindByName_thenReturnGame() {
        // when
        Optional<Game> found = gameRepository.findByName(witcher.getName());

        // then
        assertThat(found.isPresent()).isTrue();
        Game game = found.get();

        assertThat(game)
                .usingRecursiveComparison()
                .ignoringFields("id", "categories")
                .isEqualTo(witcher);
    }

//    @Test
//    public void whenFindByCategoriesContaining_thenReturnGame() {
//        // when
//        List<Game> found = gameRepository.findByCategoriesContaining(tpp);
//
//        // then
//        assertThat(found)
//                .usingRecursiveFieldByFieldElementComparatorOnFields("name", "description", "price")
//                .contains(witcher, fortnite);
//    }
}
