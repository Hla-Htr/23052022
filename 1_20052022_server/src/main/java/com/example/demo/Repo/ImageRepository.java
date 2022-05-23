package com.example.demo.Repo;

import com.example.demo.Model.Image;
import com.example.demo.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findImageByPerson(Person byId);
}
