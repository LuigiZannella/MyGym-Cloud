package it.corso.mygym.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table
public class Gym implements Serializable {

    @OneToMany(mappedBy = "gym")
    private List<Subscriptions> subscriptions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 30)
    private String address;

    @Column(unique = true)
    private int iva;




}
