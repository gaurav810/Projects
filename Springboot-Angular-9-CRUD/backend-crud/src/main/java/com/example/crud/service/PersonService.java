package com.example.crud.service;

import com.example.crud.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> listOfPerson();

    Person save(Person person);

    Person update(Long id, Person person);

    Person getDetailsById(Long id);

    void deletePerson(Long id);
}
