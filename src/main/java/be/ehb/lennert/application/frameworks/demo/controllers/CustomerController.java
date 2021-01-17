package be.ehb.lennert.application.frameworks.demo.controllers;

import be.ehb.lennert.application.frameworks.demo.dao.CustomerDAO;
import be.ehb.lennert.application.frameworks.demo.dao.ProductDAO;
import be.ehb.lennert.application.frameworks.demo.model.CRM.Customer;
import be.ehb.lennert.application.frameworks.demo.model.CRM.Order;
import be.ehb.lennert.application.frameworks.demo.model.OrderWrapper;
import be.ehb.lennert.application.frameworks.demo.model.Product;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:3000")
public class CustomerController {
    private CustomerDAO dao;

    @Autowired
    public CustomerController(CustomerDAO dao, ProductDAO productDAO){this.dao = dao;}

    // GET REQUESTS
    @GetMapping( value = "")
    @ResponseBody
    public Iterable<Customer> findAllUsers(){return dao.findAll();}

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Optional<Customer> findUserById(@PathVariable(name = "id") String id ){
        return dao.findById(id);
    }

    //POST request
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Customer addNewUser(@RequestBody Customer newCustomer){

        dao.save(newCustomer);
        System.out.println("Created: " + newCustomer.getId());
        return newCustomer;
    }


}
