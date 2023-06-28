package com.example.hamro_barber.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
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

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @Nullable
    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL)
    private List<Services> services;
}
