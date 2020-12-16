package be.ehb.lennert.application.frameworks.demo.dao;

import be.ehb.lennert.application.frameworks.demo.model.products.Accessory;
import org.springframework.data.repository.CrudRepository;

public interface AccessoryDAO extends CrudRepository<Accessory,Long> {
}
