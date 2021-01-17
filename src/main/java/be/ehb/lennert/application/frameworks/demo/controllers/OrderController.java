package be.ehb.lennert.application.frameworks.demo.controllers;

import be.ehb.lennert.application.frameworks.demo.dao.CustomerDAO;
import be.ehb.lennert.application.frameworks.demo.dao.OrderDAO;
import be.ehb.lennert.application.frameworks.demo.dao.ProductDAO;
import be.ehb.lennert.application.frameworks.demo.model.CRM.Customer;
import be.ehb.lennert.application.frameworks.demo.model.CRM.Order;
import be.ehb.lennert.application.frameworks.demo.model.OrderWrapper;
import be.ehb.lennert.application.frameworks.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/api/order")
@CrossOrigin("http://localhost:3000")
public class OrderController {
    private CustomerDAO customerDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;

    @Autowired
    public OrderController(CustomerDAO customerDAO, ProductDAO productDAO, OrderDAO orderDAO){this.customerDAO = customerDAO; this.productDAO = productDAO;this.orderDAO = orderDAO;}

    @GetMapping( value = "")
    @ResponseBody
    public Iterable<Order> findAllUsers(){return orderDAO.findAll();}

    @GetMapping(value = "/get")
    @ResponseBody
    public Iterable<Product> findOrdersFromUser(@RequestParam(name = "client_id") String id ){
    ArrayList<Long> response = customerDAO.findOrderForClient(id);

    ArrayList<Product> products = new ArrayList<>(response.size());
    for(Long i : response){
        var newProd = productDAO.findById(i);
        if(newProd.isPresent())products.add(newProd.get());
    }

    return products;
    }

    @PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> addOrder(@RequestBody OrderWrapper newOrder ){
        if(newOrder.getUserId() == null || newOrder.getProducts() == null) return null;

        Order placedOrder = new Order();
        ArrayList<Product> orderedProds = new ArrayList<Product>();

        var valUser = customerDAO.findById(newOrder.getUserId());
        if(valUser.isPresent())  placedOrder.setUser(valUser.get());

        for (Long i:newOrder.getProducts()) {
            var val = productDAO.findById(i);
            if(val.isPresent()) orderedProds.add(val.get());
        }
        placedOrder.setProduct(orderedProds);
        orderDAO.save(placedOrder);

        return new ResponseEntity<Object>(placedOrder, HttpStatus.OK);
    }

}
