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
@Builder
public class Game {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private double price;

    @ManyToMany
    @Cascade(CascadeType.ALL)
    @JoinTable(
            name = "game_of_category",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany(mappedBy = "games")
    private List<AppUser> users;
}
