package com.example.demo.Service.impl;


import com.example.demo.DTO.DetailPersonWorkDTO;
import com.example.demo.Model.DetailPerSonWork;
import com.example.demo.Repo.DetailPersonWorkRepository;
import com.example.demo.Repo.PersonRepository;
import com.example.demo.Repo.WorkRepository;
import com.example.demo.Service.DetailPersonWorkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DetailPersonWorkServiceImpl implements DetailPersonWorkService {

    DetailPersonWorkRepository repository ;
    PersonRepository personRepository;
    WorkRepository workRepository;

    @Override
    public List<DetailPerSonWork> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<DetailPerSonWork> getById(int id) {
        Optional<DetailPerSonWork> optional = repository.findById(id);
        if(!optional.isPresent()) return null;

        return optional;
    }

    @Override
    public DetailPerSonWork create(DetailPersonWorkDTO dto) {
        DetailPerSonWork detailPerSonWork = new DetailPerSonWork();
        if (dto.getPersonId() != null)
        {
            detailPerSonWork.setPerson(personRepository.findById(dto.getPersonId()).get());
        }
        if (dto.getWorkId() != null)
        {
            detailPerSonWork.setWork(workRepository.findById(dto.getWorkId()).get());
        }
        if(dto.getRole() != null)
        {
            detailPerSonWork.setRole(dto.getRole());
        }

        return repository.saveAndFlush(detailPerSonWork);
    }

    @Override
    public DetailPerSonWork update(DetailPersonWorkDTO dto, int id) {
        Optional<DetailPerSonWork> optional = repository.findById(id);
        if(!optional.isPresent()) return null;
        DetailPerSonWork detailPerSonWork = optional.get();
        if (dto.getPersonId() != null)
        {
            detailPerSonWork.setPerson(personRepository.findById(dto.getPersonId()).get());
        }
        if (dto.getWorkId() != null)
        {
            detailPerSonWork.setWork(workRepository.findById(dto.getWorkId()).get());
        }
        if(dto.getRole() != null)
        {
            detailPerSonWork.setRole(dto.getRole());
        }

        return repository.saveAndFlush(detailPerSonWork);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
