package be.ehb.lennert.application.frameworks.demo.dao;

import be.ehb.lennert.application.frameworks.demo.model.Product;
import be.ehb.lennert.application.frameworks.demo.model.products.Animal;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product,Long>  {
}
