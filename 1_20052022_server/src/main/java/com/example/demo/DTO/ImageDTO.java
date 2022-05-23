package com.example.demo.DTO;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageDTO {
    private Integer id;
    private String linkImage;
    private Integer personId;
    private String description;
}
