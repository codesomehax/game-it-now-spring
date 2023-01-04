package pl.lodz.uni.edu.gin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.lodz.uni.edu.gin.entities.Category;
import pl.lodz.uni.edu.gin.entities.Game;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Optional<Game> findByName(String name);
    @Query("SELECT g FROM Game g " +
            "JOIN g.categories c " +
            "WHERE c.name = :category_name")
    List<Game> findByCategoriesContaining(@Param("category_name") String category);
}
