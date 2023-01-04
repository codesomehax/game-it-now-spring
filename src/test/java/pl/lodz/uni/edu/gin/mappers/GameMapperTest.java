package pl.lodz.uni.edu.gin.mappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.lodz.uni.edu.gin.Stubs;
import pl.lodz.uni.edu.gin.dto.GameDto;
import pl.lodz.uni.edu.gin.entities.Category;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameMapperTest extends Stubs {
    @Autowired
    private GameMapper mapper;

    @Test
    public void shouldMapToDto() {
        GameDto dto = mapper.gameToDto(witcher);

        assertThat(dto)
                .usingRecursiveComparison()
                .ignoringFields("categories")
                .isEqualTo(witcher);

        assertThat(dto.categories())
                .containsAll(witcher.getCategories().stream()
                        .map(Category::getName)
                        .toList());
    }
}
