package com.example.springjpaorder.controller;

import com.example.springjpaorder.controller.dto.request.CreateMealRequest;
import com.example.springjpaorder.controller.dto.request.UpdateMealRequest;
import com.example.springjpaorder.controller.dto.response.StatusResponse;
import com.example.springjpaorder.model.entity.Meal;
import com.example.springjpaorder.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Meal")
public class MealController {
    @Autowired
    private MealService mealService;

    @GetMapping()
    public List<Meal> getAllMeal(){
        return this.mealService.getMealList();
    }

    @GetMapping("{id}")
    public Meal getMealById(@PathVariable int id){
        return this.mealService.getMealById(id);
    }

    @PostMapping()
    public StatusResponse addMeal(@RequestBody CreateMealRequest createMealRequest){
        String addMealStatus=this.mealService.addMeal(createMealRequest);
        return  new StatusResponse(addMealStatus);
    }

    @PutMapping("{id}")
    public StatusResponse updateMeal(@RequestBody UpdateMealRequest updateMealRequest,@PathVariable int id){
        String updateMealStatus=this.mealService.updateMeal(updateMealRequest,id);
        return new StatusResponse(updateMealStatus);
    }

    @DeleteMapping("{id}")
    public StatusResponse deleteMeal(@PathVariable int id){
        String deleteMealStatus=this.mealService.deleteMeal(id);
        return new StatusResponse(deleteMealStatus);
    }
}
