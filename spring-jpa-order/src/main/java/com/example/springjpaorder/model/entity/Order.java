package com.example.springjpaorder.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orders")
public class Order {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int totalPrice;

    @Column
    private String waiter;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Order_id", referencedColumnName = "id")
    private List<OrderItem> orderItemList;

    public int getTotalPrice() {
        for (OrderItem orderItem : this.orderItemList) {
            this.totalPrice += orderItem.getQuantity() * orderItem.getPrice();
        }
        return this.totalPrice;
    }
}
