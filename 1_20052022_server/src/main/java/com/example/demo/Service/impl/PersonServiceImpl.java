package com.example.demo.Service.impl;

import com.example.demo.DTO.PersonDTO;
import com.example.demo.Model.Image;
import com.example.demo.Model.Person;
import com.example.demo.Repo.PersonRepository;
import com.example.demo.Service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    PersonRepository repository;


    @Override
    public List<Person> getAllPerson() {
        return repository.findAll();
    }

    @Override
    public Optional<Person> getPersonById(int id) {
        Optional<Person> optional = repository.findById(id);
        if(!optional.isPresent())
        {
            return null;
        }
        return optional;
    }

    @Override
    public Person create(PersonDTO dto) {
        Person person = new Person();
        Date date = new Date();

        if(dto.getName() != null)
        {
            person.setName(dto.getName());
        }
        if(dto.getNationality() != null)
        {
            person.setNationality(dto.getNationality());
        }
        if(dto.getNation() != null)
        {
            person.setNation(dto.getNation());
        }
        if(dto.getJob() != null)
        {
            person.setJob(dto.getJob());
        }
        if(dto.getHomeTown() != null)
        {
            person.setHomeTown(dto.getHomeTown());
        }
        if(dto.getFamousFor() != null)
        {
            person.setFamousFor(dto.getFamousFor());
        }
        if(dto.getHeight() != null)
        {
            person.setHeight(dto.getHeight());
        }
        if(dto.getWeight() != null)
        {
            person.setWeight(dto.getWeight());
        }
        if(dto.getNickname() != null)
        {
            person.setNickname(dto.getNickname());
        }
        if(dto.getSchool() != null)
        {
            person.setSchool(dto.getSchool());
        }
        if(dto.getRepresentative() != null)
        {
            person.setRepresentative(dto.getRepresentative());
        }

        person.setDescription(dto.getDescription());

        person.setCreateDate(date);
        return repository.saveAndFlush(person);
    }

    @Override
    public Person update(PersonDTO dto, int id) {
        Optional<Person> optional = repository.findById(id);
        if(!optional.isPresent())
        {
            return null;
        }
        Person person = optional.get();
        Date date = new Date();

        if(dto.getName() != null)
        {
            person.setName(dto.getName());
        }
        if(dto.getNationality() != null)
        {
            person.setNationality(dto.getNationality());
        }
        if(dto.getNation() != null)
        {
            person.setNation(dto.getNation());
        }
        if(dto.getJob() != null)
        {
            person.setJob(dto.getJob());
        }
        if(dto.getHomeTown() != null)
        {
            person.setHomeTown(dto.getHomeTown());
        }
        if(dto.getFamousFor() != null)
        {
            person.setFamousFor(dto.getFamousFor());
        }
        if(dto.getHeight() != null)
        {
            person.setHeight(dto.getHeight());
        }
        if(dto.getWeight() != null)
        {
            person.setWeight(dto.getWeight());
        }
        if(dto.getNickname() != null)
        {
            person.setNickname(dto.getNickname());
        }
        if(dto.getSchool() != null)
        {
            person.setSchool(dto.getSchool());
        }
        if(dto.getRepresentative() != null)
        {
            person.setRepresentative(dto.getRepresentative());
        }

        person.setDescription(dto.getDescription());

        person.setUpdateDate(date);
        return repository.saveAndFlush(person);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
