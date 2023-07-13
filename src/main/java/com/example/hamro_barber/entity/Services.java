package com.example.hamro_barber.entity;

import com.example.hamro_barber.constants.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String serviceName;
    private String fee;
    private String serviceTimeInMinutes;
    @ManyToOne(fetch = FetchType.LAZY)
    private Barber barber;
    private Category category;
}
