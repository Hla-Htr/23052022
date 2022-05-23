package com.example.demo.DTO;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class WorkDTO {
    private Integer id;
    private String name;
    private String description;
    private String image;
}
