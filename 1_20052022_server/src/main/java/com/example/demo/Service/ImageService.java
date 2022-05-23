package com.example.demo.Service;

import com.example.demo.DTO.ImageDTO;
import com.example.demo.Model.Image;

import java.util.List;
import java.util.Optional;

public interface ImageService {

    List<Image> getAllImage();
    Optional<Image> getImageById(int id);
    List<Image> getImageByPersonId(int personId);
    Image create(ImageDTO dto);
    Image update(ImageDTO dto, int id);
    void delete(int id);
}
