package com.example.hamro_barber.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

<<<<<<< Updated upstream:src/main/java/com/example/hamro_barber/model/Favourite.java
import javax.persistence.*;
import javax.validation.constraints.NotNull;
=======
//import javax.persistence.*;
>>>>>>> Stashed changes:src/main/java/com/example/hamro_barber/entity/Barber.java
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany
    @Nullable
    private List<Barber> barbers;
    @OneToMany
    @Nullable
    private List<Services> services;
    @OneToOne
    @NotNull
    private Customer customer;
}
