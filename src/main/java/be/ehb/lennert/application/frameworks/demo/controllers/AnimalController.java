package be.ehb.lennert.application.frameworks.demo.controllers;

import be.ehb.lennert.application.frameworks.demo.dao.AnimalDAO;
import be.ehb.lennert.application.frameworks.demo.model.products.Animal;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/animals")
public class AnimalController {

    private AnimalDAO dao;
    public AnimalController(AnimalDAO dao){this.dao = dao;}

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Animal> findAllAnimals(){return dao.findAll();}

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Animal> findAnimalById(@PathVariable(name = "id") Long id ){
        return dao.findById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "create" ,method = RequestMethod.POST)
    @ResponseBody
    public Animal addNewAnimal(@RequestBody Animal newCourse){
        //Course temp = new Course(0,name,desc,price);
        dao.save(newCourse);
        return newCourse;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteAnimal(@PathVariable(name = "id") Long id){
        dao.deleteById(id);
        return HttpStatus.OK;
    }

}