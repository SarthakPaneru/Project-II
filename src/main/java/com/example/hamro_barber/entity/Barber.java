package com.example.hamro_barber.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String panNo;
    private boolean isOpened;

    @OneToOne
    @JoinColumn(unique = true, referencedColumnName = "id")
    private User user;

    @Nullable
    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL)
    private List<Services> services;

    private String imageUrl;
    private Integer rating;
}
