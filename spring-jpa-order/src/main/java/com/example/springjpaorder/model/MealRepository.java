package com.example.springjpaorder.model;

import com.example.springjpaorder.model.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  MealRepository extends JpaRepository<Meal,Integer> {
    Meal findById(int id);
    Meal findByName(String name);
}
