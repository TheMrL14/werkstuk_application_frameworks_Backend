package be.ehb.lennert.application.frameworks.demo.dao;

import be.ehb.lennert.application.frameworks.demo.model.CRM.Order;
import be.ehb.lennert.application.frameworks.demo.model.products.Food;
import org.springframework.data.repository.CrudRepository;

public interface OrderDAO extends CrudRepository<Order,Long> {

}
