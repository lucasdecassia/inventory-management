package com.lm.inventory_management.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    public Product(@NonNull String name, @NonNull int quantity, @NonNull double price) {
        validade(name, quantity, price);
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    private void validade(String name, int qty, double price) {
        if (name == null || name.isEmpty()) {
            System.out.println("Error: Product name cannot be empty");
            return;
        }
        if (qty < 0) {
            System.out.println("Error: Quantity cannot be negative");
            return;
        }
        if (price < 0) {
            System.out.println("Error: Price cannot be negative");
            return;
        }
    }

}
