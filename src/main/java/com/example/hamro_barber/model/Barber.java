package com.example.hamro_barber.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
    @NotNull
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

    @Column(name = "longitude", precision = 10, scale = 7)
    private BigDecimal longitude;
    @Column(name = "latitude", precision = 10, scale = 7)
    private BigDecimal latitude;
    @Transient
    private Double distance;
}
