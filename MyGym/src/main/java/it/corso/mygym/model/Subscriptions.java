package it.corso.mygym.model;


import it.corso.mygym.model.enums.SubscriptionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table
public class Subscriptions implements Serializable {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @JoinColumn(name = "gym_id")
    @ManyToOne
    private Gym gym;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private SubscriptionType type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "Questo campo è obbligatorio")
    @Column(nullable = false)
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "Questo campo è obbligatorio")
    @Column(nullable = false)
    private LocalDate endDate;

    @NotEmpty(message = "Questo campo è obbligatorio")
    @Column(nullable = false)
    @DecimalMin("0.00")
    private double price;
    @Column
    private boolean sold = true;

}

