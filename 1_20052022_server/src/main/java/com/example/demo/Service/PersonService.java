package com.example.demo.Service;


import com.example.demo.DTO.ImageDTO;
import com.example.demo.DTO.PersonDTO;
import com.example.demo.Model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> getAllPerson();
    Optional<Person> getPersonById(int id);
    Person create(PersonDTO dto);
    Person update(PersonDTO dto, int id);
    void delete(int id);
}
