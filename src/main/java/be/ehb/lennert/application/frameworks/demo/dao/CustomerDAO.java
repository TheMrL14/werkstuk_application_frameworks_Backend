package be.ehb.lennert.application.frameworks.demo.dao;

import be.ehb.lennert.application.frameworks.demo.model.CRM.Customer;
import be.ehb.lennert.application.frameworks.demo.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface CustomerDAO extends CrudRepository<Customer, String> {

    // search order(s) for client and create list with Ids from all products ordered
    @Query(value = "SELECT op.product_id FROM order_products op WHERE op.order_id IN ( SELECT o.id FROM orders o WHERE o.customer_id = :client_id )",nativeQuery = true )
            public ArrayList<Long> findOrderForClient(@Param("client_id") String client_id);

}
