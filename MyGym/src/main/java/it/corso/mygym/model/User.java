package it.corso.mygym.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class User implements Serializable {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @OneToMany(mappedBy = "user")
    private List<Subscriptions> subscriptions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    @NotEmpty(message = "Questo campo è obbligatorio")
    private String name;

    @Column(length = 30, nullable = false)
    @NotEmpty(message = "Questo campo è obbligatorio")
    private String surname;

    @Column(length = 50, unique = true)
    private String email;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Column
    private boolean certificate;

    @Column
    private boolean activate = true;
}
