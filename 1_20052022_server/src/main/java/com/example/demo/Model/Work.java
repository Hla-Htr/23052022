package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name="work_generator", sequenceName = "work_seq")
    private int id;
    @Column(name="name", length=200)
    private String name;
    @Column(name="description", length=1000)
    private String description;
    private String image;
    @CreatedDate
    private Date createDate;
    private Date updateDate;


    @OneToMany(mappedBy = "work",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<DetailPerSonWork> detailPerSonWorks;


}
