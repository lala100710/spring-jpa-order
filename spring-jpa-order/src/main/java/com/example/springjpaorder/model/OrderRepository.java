package com.example.springjpaorder.model;


import com.example.springjpaorder.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order,Integer> {
    Order findById(int id);

}
