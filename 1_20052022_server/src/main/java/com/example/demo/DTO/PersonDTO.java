package com.example.demo.DTO;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class PersonDTO {
    private Integer id;
    private String name;
    private String nationality;
    private String nickname;
    private String nation;
    private String school;
    private String job;
    private Integer yearOfOperation;
    private String representative;
    private String famousFor;
    private String homeTown;
    private Integer height;
    private Integer weight;
    private String description;
}
