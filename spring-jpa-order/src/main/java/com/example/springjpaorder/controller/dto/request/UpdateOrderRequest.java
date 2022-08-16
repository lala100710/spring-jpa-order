package com.example.springjpaorder.controller.dto.request;

import com.example.springjpaorder.model.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderRequest {
    private String waiter;

    private List<OrderItem> orderItemList;
}
