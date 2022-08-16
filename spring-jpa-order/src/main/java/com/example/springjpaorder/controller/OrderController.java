package com.example.springjpaorder.controller;

import com.example.springjpaorder.controller.dto.request.CreateOrderRequest;
import com.example.springjpaorder.controller.dto.request.UpdateOrderRequest;
import com.example.springjpaorder.controller.dto.response.StatusResponse;
import com.example.springjpaorder.model.entity.Order;
import com.example.springjpaorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public List<Order>getAllOrder(){
        return this.orderService.getAllOrder();
    }

    @GetMapping("{id}")
    public Order getOrderById(@PathVariable int id){
            return this.orderService.getOrderById(id);
    }

    @PostMapping()
    public StatusResponse addOrder(@RequestBody CreateOrderRequest createOrderRequest){
        String status=this.orderService.addOrder(createOrderRequest);
        return new StatusResponse(status);
    }

    @PutMapping("{id}")
    public StatusResponse updateOrder(@PathVariable int id, @RequestBody UpdateOrderRequest updateOrderRequest){
        String status=this.orderService.updateOrder(id,updateOrderRequest);
        return new StatusResponse(status);
    }

    @DeleteMapping("{id}")
    public StatusResponse deleteOrder(@PathVariable int id){
        String status=this.orderService.deleteOrderById(id);
        return new StatusResponse(status);
    }
}
