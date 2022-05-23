package com.example.demo.Service;

import com.example.demo.DTO.DetailPersonWorkDTO;
import com.example.demo.Model.DetailPerSonWork;

import java.util.List;
import java.util.Optional;

public interface DetailPersonWorkService {
    List<DetailPerSonWork> getAll();
    Optional<DetailPerSonWork> getById(int id);
    DetailPerSonWork create(DetailPersonWorkDTO dto);
    DetailPerSonWork update(DetailPersonWorkDTO dto, int id);
    void delete(int id);
}
