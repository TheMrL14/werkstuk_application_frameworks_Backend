package be.ehb.lennert.application.frameworks.demo.dao;

import be.ehb.lennert.application.frameworks.demo.model.CRM.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDAO extends CrudRepository<Customer, String> {
}
