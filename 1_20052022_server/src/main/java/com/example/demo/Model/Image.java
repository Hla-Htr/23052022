package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="link_image", length=400)
    private String linkImage;
    @Column(name="description", length=1000)
    private String description;
    @CreatedDate
    private Date createDate;
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
