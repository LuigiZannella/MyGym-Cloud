package it.corso.mygym.model.dto;

import it.corso.mygym.model.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SubDTO {

    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private double price;

    private boolean sold = true;
}