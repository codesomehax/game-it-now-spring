package pl.lodz.uni.edu.gin.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Game {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private double price;
    @ManyToMany
    @JoinTable(
            name = "game_of_category",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Cascade(CascadeType.ALL)
    private List<Category> categories;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id && Double.compare(game.price, price) == 0 && Objects.equals(name, game.name) && Objects.equals(description, game.description) && Objects.equals(categories, game.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, categories);
    }
}
