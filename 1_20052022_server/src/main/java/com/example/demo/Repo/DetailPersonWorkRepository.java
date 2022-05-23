package com.example.demo.Repo;

import com.example.demo.Model.DetailPerSonWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailPersonWorkRepository extends JpaRepository<DetailPerSonWork, Integer> {
}
