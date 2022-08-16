package com.example.springjpaorder.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ORDER_ITEM")
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int itemId;

    @Column
    private int quantity;

    @Column
    private String mealName;

    @Column
    private int price;

}
