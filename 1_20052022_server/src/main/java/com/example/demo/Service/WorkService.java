package com.example.demo.Service;

import com.example.demo.DTO.PersonDTO;
import com.example.demo.DTO.WorkDTO;
import com.example.demo.Model.Work;


import java.util.List;
import java.util.Optional;

public interface WorkService {
    List<Work> getAllWork();
    Optional<Work> getWorkById(int id);
    Work create(WorkDTO dto);
    Work update(WorkDTO dto, int id);
    void delete(int id);
}
