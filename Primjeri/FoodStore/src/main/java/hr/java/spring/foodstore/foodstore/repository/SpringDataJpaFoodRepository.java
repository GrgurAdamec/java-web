package hr.java.spring.foodstore.foodstore.repository;

import hr.java.spring.foodstore.foodstore.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataJpaFoodRepository extends JpaRepository<FoodItem, Integer> {
    List<FoodItem> findByName(String name);
}
