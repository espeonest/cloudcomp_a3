package com.example.assignment3_ChhetriHallRogers.controller;

import com.example.assignment3_ChhetriHallRogers.model.Shoes;
import com.example.assignment3_ChhetriHallRogers.repository.ShoesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "")
@RestController
public class ShoesController {
    private final ShoesRepository repository;

    public ShoesController(ShoesRepository repository) {
        this.repository = repository;
    }

    // GET /shoes
    @GetMapping("/shoes")
    public List<Shoes> getAllShoes() {
        return repository.findAll();
    }

    // GET shoe by ID
    @GetMapping("shoes/{shoeID}")
    public Shoes getShoeById(@PathVariable ("shoeID") int shoeID) {
        return repository.findById(shoeID);
    }

    @PostMapping("/shoes")
    public Shoes addNewShoe(@RequestBody Shoes shoe) {
       repository.save(shoe);
        return shoe; // return the created object
    }

    //Update Shoe by ID
    @PutMapping("shoes/{shoeID}")
    public Shoes updateShoeById(@PathVariable("shoeID") int shoeID, @RequestBody Shoes updatedShoe) {
        Shoes existingShoe = repository.findById(shoeID);

        if (existingShoe != null) {
            //Update logic
            existingShoe.setName(updatedShoe.getName());
            existingShoe.setDescription(updatedShoe.getDescription());
            existingShoe.setPrice(updatedShoe.getPrice());
            existingShoe.setSku(updatedShoe.getSku());
            existingShoe.setActive(updatedShoe.isActive());
            existingShoe.setImage(updatedShoe.getImage());

            repository.update(existingShoe);

            return existingShoe;

        }else{
            return null;
        }
    }

    //Delete Shoe by ID
    @DeleteMapping("shoes/{shoeID}")
    public Shoes deleteShoeById(@PathVariable ("shoeID") int shoeID) {
        Shoes shoe = repository.findById(shoeID);
        repository.delete(shoeID);
        return shoe;
    }



}
