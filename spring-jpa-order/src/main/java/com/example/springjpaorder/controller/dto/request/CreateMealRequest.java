package com.example.springjpaorder.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMealRequest {
    private String name;

    private String description;

    private int price;
}
