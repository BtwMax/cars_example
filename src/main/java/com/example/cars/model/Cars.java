package com.example.cars.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String color;

    @Column
    private int horsepower;

    @Column
    private int quantity;

    @Column
    private int taxAmount;

}
