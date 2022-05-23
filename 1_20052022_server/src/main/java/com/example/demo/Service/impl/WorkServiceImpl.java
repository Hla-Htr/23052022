package com.example.demo.Service.impl;

import com.example.demo.DTO.WorkDTO;
import com.example.demo.Model.Work;
import com.example.demo.Repo.WorkRepository;
import com.example.demo.Service.WorkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkServiceImpl implements WorkService {

    WorkRepository repository;

    @Override
    public List<Work> getAllWork() {
        return repository.findAll();
    }

    @Override
    public Optional<Work> getWorkById(int id) {
        Optional<Work> optional = repository.findById(id);
        if(!optional.isPresent())
        {
            return null;
        }
        return optional;
    }

    @Override
    public Work create(WorkDTO dto) {

        Work work = new Work();
        Date date = new Date();

        if(dto.getName() != null)
        {
            work.setName(dto.getName());
        }
        if(dto.getImage() != null)
        {
            work.setImage(dto.getImage());
        }

        work.setDescription(dto.getDescription());
        work.setCreateDate(date);
        return repository.saveAndFlush(work);
    }

    @Override
    public Work update(WorkDTO dto, int id) {
        Optional<Work> optional = repository.findById(id);
        if(!optional.isPresent()) return null;
        Work work = optional.get();
        Date date = new Date();

        if(dto.getName() != null)
        {
            work.setName(dto.getName());
        }
        if(dto.getImage() != null)
        {
            work.setImage(dto.getImage());
        }

        work.setDescription(dto.getDescription());
        work.setUpdateDate(date);
        return repository.saveAndFlush(work);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
