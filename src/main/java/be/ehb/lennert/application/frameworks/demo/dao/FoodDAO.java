package be.ehb.lennert.application.frameworks.demo.dao;

import be.ehb.lennert.application.frameworks.demo.model.products.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodDAO extends CrudRepository<Food,Long> {
}
