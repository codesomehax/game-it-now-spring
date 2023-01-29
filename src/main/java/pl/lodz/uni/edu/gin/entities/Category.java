package pl.lodz.uni.edu.gin.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Category {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private List<Game> gamesOfCategory;
}
