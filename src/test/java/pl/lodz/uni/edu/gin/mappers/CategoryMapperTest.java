package pl.lodz.uni.edu.gin.mappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.lodz.uni.edu.gin.Stubs;
import pl.lodz.uni.edu.gin.dto.CategoryDto;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryMapperTest extends Stubs {
    @Autowired
    private CategoryMapper mapper;

    @Test
    public void shouldMapToDto() {
        CategoryDto dto = mapper.categoryToDto(rpg);

        assertThat(dto)
                .usingRecursiveComparison()
                .isEqualTo(rpg);
    }
}
