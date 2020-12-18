package be.ehb.lennert.application.frameworks.demo.controllers;

import be.ehb.lennert.application.frameworks.demo.dao.AnimalDAO;
import be.ehb.lennert.application.frameworks.demo.dao.ProductDAO;
import be.ehb.lennert.application.frameworks.demo.model.Product;
import be.ehb.lennert.application.frameworks.demo.model.ProductType;
import be.ehb.lennert.application.frameworks.demo.model.products.Animal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/products")
public class ProductController {


    private ProductDAO dao;
    public ProductController(ProductDAO dao){this.dao = dao;}

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Product> findAllAnimals(){return dao.findAll();}

    @CrossOrigin (origins = "http://localhost:3000")
    @RequestMapping(value = "types",method = RequestMethod.GET)
    @ResponseBody
    public String[] getTypes(){return ProductType.getTypes();}
}
