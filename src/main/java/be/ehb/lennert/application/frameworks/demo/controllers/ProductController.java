package be.ehb.lennert.application.frameworks.demo.controllers;

import be.ehb.lennert.application.frameworks.demo.dao.AccessoryDAO;
import be.ehb.lennert.application.frameworks.demo.dao.AnimalDAO;
import be.ehb.lennert.application.frameworks.demo.dao.FoodDAO;
import be.ehb.lennert.application.frameworks.demo.dao.ProductDAO;
import be.ehb.lennert.application.frameworks.demo.model.Product;
import be.ehb.lennert.application.frameworks.demo.model.ProductType;
import be.ehb.lennert.application.frameworks.demo.model.products.Animal;
import be.ehb.lennert.application.frameworks.demo.model.products.Food;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    private AnimalDAO animalDAO;
    private FoodDAO foodDAO;
    private AccessoryDAO accessoryDAO;
    private ProductDAO dao;
    public ProductController(ProductDAO dao){
        this.dao = dao;
    }


    // GET REQUESTS
    @GetMapping( value = "")
    @ResponseBody
    public Iterable<Product> findAllProducts(){return dao.findAll();}

    @GetMapping( value = "/types")
    @ResponseBody
    public String[] getTypes(){return ProductType.getTypes();}

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Optional<Product> findProductById(@PathVariable(name = "id") Long id ){
        return dao.findById(id);
    }

    // POST/DELETE REQUESTS

    @PostMapping(value = "/create")
    @ResponseBody
    public Product addNewProduct(@RequestBody Product newProduct){
        //Course temp = new Course(0,name,desc,price);
        dao.save(newProduct);
        return newProduct;
    }

    @DeleteMapping(value = "delete/{id}")
    public HttpStatus deleteProduct(@PathVariable(name = "id") Long id){
        dao.deleteById(id);
        return HttpStatus.OK;
    }

}
