package com.example.springjpaorder.service;

import com.example.springjpaorder.controller.dto.request.CreateMealRequest;
import com.example.springjpaorder.controller.dto.request.UpdateMealRequest;
import com.example.springjpaorder.model.MealRepository;
import com.example.springjpaorder.model.entity.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {
    @Autowired
    private MealRepository mealRepository;


    public List<Meal> getMealList() {
        return this.mealRepository.findAll();
    }

    public Meal getMealById(int id) {
        return this.mealRepository.findById(id);
    }

    public String addMeal(CreateMealRequest createMealRequest) {
        Meal meal = new Meal();

        if (null ==createMealRequest.getDescription() || createMealRequest.getDescription().isEmpty()){
            return "輸入錯誤 不得為空值";
        }else {
            meal.setDescription(createMealRequest.getDescription());
        }

        if (null ==createMealRequest.getName() || createMealRequest.getName().isEmpty()){
            return "輸入錯誤 不得為空值";
        }else {
            meal.setName(createMealRequest.getName());
        }

       if (createMealRequest.getPrice()<=0){
           return "價錢必須大於0";
       }else {
           meal.setPrice(createMealRequest.getPrice());
       }

        this.mealRepository.save(meal);
        return "OK";
    }

    public String updateMeal(UpdateMealRequest updateMealRequest, int id) {
        Meal meal = getMealById(id);
        if (null == meal) {
            return "查無此餐點";
        } else {
            if (null != updateMealRequest.getName()){
                meal.setName(updateMealRequest.getName());
            }

            if (null != updateMealRequest.getDescription()){
                meal.setDescription(updateMealRequest.getDescription());
            }

            if (updateMealRequest.getPrice() > 0){
                meal.setPrice(updateMealRequest.getPrice());
            }else {
                return "價錢必須大於0";
            }

            this.mealRepository.save(meal);
            return "ok";
        }
    }

    public String deleteMeal(int id) {
        Meal meal = getMealById(id);
        if (null == meal) {
            return "查無此餐點";
        } else {
            this.mealRepository.delete(meal);
            return "ok";
        }
    }

}
