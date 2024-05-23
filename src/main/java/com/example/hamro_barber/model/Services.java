package com.example.hamro_barber.model;

<<<<<<< Updated upstream:src/main/java/com/example/hamro_barber/model/Services.java
import com.example.hamro_barber.constants.Category;
=======
import jakarta.persistence.*;
>>>>>>> Stashed changes:src/main/java/com/example/hamro_barber/entity/Services.java
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.*;

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
    @Enumerated(EnumType.STRING)
    private Category category;
}
