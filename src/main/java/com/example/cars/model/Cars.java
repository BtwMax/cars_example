package com.example.cars.model;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "cars")
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "horsepower", nullable = false)
    private int horsepower;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public Long getId() {
        return id;
    }

    public Cars() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars = (Cars) o;
        return horsepower == cars.horsepower &&
                quantity == cars.quantity &&
                Objects.equals(id, cars.id) &&
                Objects.equals(color, cars.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, horsepower, quantity);
    }

    @Override
    public String toString() {
        return "Cars{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", horsepower=" + horsepower +
                ", quantity=" + quantity +
                '}';
    }

    public boolean IsInvalid() {
        return (color == null || color.equals("") || horsepower <= 0 || quantity <= 0);
    }
}
