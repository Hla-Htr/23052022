package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String nationality;
    private String nickname;
    private String nation;
    private String school;
    private String job;
    private int yearOfOperation;
    private String representative;
    private String famousFor;
    private String homeTown;
    private int height;
    private int weight;
    @Column(name="description", length=1000)
    private String description;
    @CreatedDate
    private Date createDate;
    private Date updateDate;


    @OneToMany(mappedBy = "person",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<DetailPerSonWork> detailPerSonWorks;

    @OneToMany(mappedBy = "person",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Image> listImage;
}
