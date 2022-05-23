package com.example.demo.Service.impl;

import com.example.demo.DTO.ImageDTO;
import com.example.demo.Model.Image;
import com.example.demo.Repo.ImageRepository;
import com.example.demo.Repo.PersonRepository;
import com.example.demo.Service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    ImageRepository repository;

    PersonRepository personRepository;

    @Override
    public List<Image> getAllImage() {
        return repository.findAll();
    }

    @Override
    public Optional<Image> getImageById(int id) {
        Optional<Image> image = repository.findById(id);

        if(!image.isPresent())
        {
            return null;
        }
        return image;
    }

    @Override
    public List<Image> getImageByPersonId(int personId) {
        if(!personRepository.findById(personId).isPresent())
            return null;
        return repository.findImageByPerson(personRepository.findById(personId).get());
    }

    @Override
    public Image create(ImageDTO dto) {
        Image image = new Image();
        Date date = new Date();
        if(dto.getLinkImage() != null)
        {
            image.setLinkImage(dto.getLinkImage());
        }
        if(dto.getPersonId() != null)
        {
            image.setPerson(personRepository.findById(dto.getPersonId()).get());
        }
        image.setDescription(dto.getDescription());

        image.setCreateDate(date);

        return repository.saveAndFlush(image);
    }

    @Override
    public Image update(ImageDTO dto, int id) {
        Optional<Image> optionalImage = repository.findById(id);
        Date date = new Date();

        if(!optionalImage.isPresent())
            return null;
        Image image = optionalImage.get();
        if(dto.getLinkImage() != null)
        {
            image.setLinkImage(dto.getLinkImage());
        }
        if(dto.getPersonId() != null)
        {
            image.setPerson(personRepository.findById(dto.getPersonId()).get());
        }
        image.setDescription(dto.getDescription());
        image.setUpdateDate(date);

        return repository.saveAndFlush(image);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
