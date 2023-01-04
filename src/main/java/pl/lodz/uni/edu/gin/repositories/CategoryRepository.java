package pl.lodz.uni.edu.gin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lodz.uni.edu.gin.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
