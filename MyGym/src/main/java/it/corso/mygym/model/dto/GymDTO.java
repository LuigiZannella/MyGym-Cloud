package it.corso.mygym.model.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class GymDTO {

    private Long id;

    private String name;

    private String email;

    private String address;

    private int iva;
}
