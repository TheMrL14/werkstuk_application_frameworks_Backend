package be.ehb.lennert.application.frameworks.demo.dao;

import be.ehb.lennert.application.frameworks.demo.model.products.Animal;
import org.springframework.data.repository.CrudRepository;

public interface AnimalDAO extends CrudRepository<Animal,Long> {
}
