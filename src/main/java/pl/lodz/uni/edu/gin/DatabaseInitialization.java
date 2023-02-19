package pl.lodz.uni.edu.gin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.lodz.uni.edu.gin.entities.AppUser;
import pl.lodz.uni.edu.gin.entities.Category;
import pl.lodz.uni.edu.gin.entities.Game;
import pl.lodz.uni.edu.gin.repositories.AppUserRepository;
import pl.lodz.uni.edu.gin.repositories.CategoryRepository;
import pl.lodz.uni.edu.gin.repositories.GameRepository;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseInitialization implements InitializingBean {
    private final GameRepository gameRepository;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        Category rpg = Category.builder()
                .name("RPG")
                .description("Role playing game")
                .build();

        Category shooter = Category.builder()
                .name("Shooter")
                .description("Shooting etc")
                .build();

        Category fpp = Category.builder()
                .name("FPP")
                .description("First person perspective")
                .build();

        Category tpp = Category.builder()
                .name("TPP")
                .description("Third person perspective")
                .build();

        Game witcher = Game.builder()
                .name("The Witcher")
                .description("Slash monsters etc")
                .price(100.0)
                .imageUrl("https://image.api.playstation.com/vulcan/ap/rnd/202211/0711/kh4MUIuMmHlktOHar3lVl6rY.png ")
                .categories(List.of(rpg, tpp))
                .build();

        Game callOfDuty = Game.builder()
                .name("Call of Duty")
                .description("World War")
                .price(150.0)
                .imageUrl("https://image.api.playstation.com/vulcan/img/rnd/202008/1900/lTSvbByTYMqy6R22teoybKCg.png")
                .categories(List.of(fpp, shooter))
                .build();

        Game fortnite = Game.builder()
                .name("Fortnite")
                .description("Fancy shooting")
                .price(0.0)
                .imageUrl("https://image.api.playstation.com/vulcan/ap/rnd/202212/0200/wy3SIGJqFW7nz1r0Wi48PbbL.png")
                .categories(List.of(tpp, shooter))
                .build();

        AppUser john = AppUser.builder()
                .username("John")
                .password(passwordEncoder.encode("1234"))
                .email("john@gmail.com")
                .role(AppUser.Role.USER)
                .games(List.of(witcher))
                .cart(List.of(fortnite))
                .build();

        AppUser sarah = AppUser.builder()
                .username("Sarah")
                .password(passwordEncoder.encode("4321"))
                .email("sarah@gmail.com")
                .role(AppUser.Role.ADMIN)
                .games(List.of(fortnite))
                .cart(Collections.emptyList())
                .build();

        List<Category> categoryStubs = List.of(rpg, shooter, fpp, tpp);
        List<Game> gameStubs = List.of(witcher, callOfDuty, fortnite);
        List<AppUser> userStubs = List.of(john, sarah);

        categoryRepository.saveAll(categoryStubs);
        gameRepository.saveAll(gameStubs);
        appUserRepository.saveAll(userStubs);
    }
}
