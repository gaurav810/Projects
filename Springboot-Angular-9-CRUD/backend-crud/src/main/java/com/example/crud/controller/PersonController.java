package com.example.crud.controller;

import com.example.crud.entity.Person;
import com.example.crud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getListOfUsers() {

        return personService.listOfPerson();
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {

        return personService.save(person);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@RequestBody Person person, @PathVariable("id") Long id) {

        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") Long id) {

        personService.deletePerson(id);
        return "Deleted Successfully.";
    }

    @GetMapping("/{id}")
    public Person getPersonDetailsById(@PathVariable("id") Long id) {

        return personService.getDetailsById(id);
    }
}
