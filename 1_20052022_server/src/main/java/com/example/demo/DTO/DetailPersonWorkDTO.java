package com.example.demo.DTO;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class DetailPersonWorkDTO {
    private Integer id;
    private Integer personId;
    private Integer workId;

    private String role;
}
