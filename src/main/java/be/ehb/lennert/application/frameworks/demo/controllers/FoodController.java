package be.ehb.lennert.application.frameworks.demo.controllers;

import be.ehb.lennert.application.frameworks.demo.dao.AnimalDAO;
import be.ehb.lennert.application.frameworks.demo.dao.FoodDAO;
import be.ehb.lennert.application.frameworks.demo.model.products.Animal;
import be.ehb.lennert.application.frameworks.demo.model.products.Food;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("api/food")
public class FoodController {

    private FoodDAO dao;
    public FoodController(FoodDAO dao){this.dao = dao;}

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Food> findAllFoods(){return dao.findAll();}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Food> findAnimalById(@PathVariable(name = "id") Long id ){
        return dao.findById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "create" ,method = RequestMethod.POST)
    @ResponseBody
    public Food addNewFood(@RequestBody Food newProduct){
        //Course temp = new Course(0,name,desc,price);
        dao.save(newProduct);
        return newProduct;
    }

    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteAnimal(@PathVariable(name = "id") Long id){
        dao.deleteById(id);
        return HttpStatus.OK;
    }
}
