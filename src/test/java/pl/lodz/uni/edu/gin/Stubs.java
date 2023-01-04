package pl.lodz.uni.edu.gin;

import pl.lodz.uni.edu.gin.entities.Category;
import pl.lodz.uni.edu.gin.entities.Game;

import java.util.List;

public abstract class Stubs {
    protected static final Category rpg;
    protected static final Category shooter;
    protected static final Category fpp;
    protected static final Category tpp;
    protected static final Game witcher;
    protected static final Game callOfDuty;
    protected static final Game fortnite;
    protected static final List<Category> categoryStubs;
    protected static final List<Game> gameStubs;

    static {
        rpg = new Category();
        rpg.setName("RPG");
        rpg.setDescription("Role playing game");

        shooter = new Category();
        shooter.setName("Shooter");
        shooter.setDescription("Shooting etc");

        fpp = new Category();
        fpp.setName("FPP");
        fpp.setDescription("First person perspective");

        tpp = new Category();
        tpp.setName("TPP");
        tpp.setDescription("Third person perspective");

        witcher = new Game();
        witcher.setName("The Witcher");
        witcher.setDescription("Slash monsters etc");
        witcher.setPrice(100);
        witcher.setCategories(List.of(rpg, tpp));

        callOfDuty = new Game();
        callOfDuty.setName("Call Of Duty");
        callOfDuty.setDescription("World War");
        callOfDuty.setPrice(150);
        callOfDuty.setCategories(List.of(fpp, shooter));

        fortnite = new Game();
        fortnite.setName("Fortnite");
        fortnite.setDescription("Fancy shooting");
        fortnite.setPrice(0);
        fortnite.setCategories(List.of(tpp, shooter));

        categoryStubs = List.of(rpg, shooter, fpp, tpp);
        gameStubs = List.of(witcher, callOfDuty, fortnite);
    }
}
