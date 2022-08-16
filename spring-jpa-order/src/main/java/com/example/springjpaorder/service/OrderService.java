package com.example.springjpaorder.service;

import com.example.springjpaorder.controller.dto.request.CreateOrderRequest;
import com.example.springjpaorder.controller.dto.request.UpdateOrderRequest;
import com.example.springjpaorder.model.MealRepository;
import com.example.springjpaorder.model.OrderRepository;
import com.example.springjpaorder.model.entity.Order;
import com.example.springjpaorder.model.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrder() {
        return this.orderRepository.findAll();
    }

    public Order getOrderById(int id) {
        return this.orderRepository.findById(id);
    }

    public String addOrder(CreateOrderRequest createOrderRequest) {
        Order order = new Order();
        if (null == createOrderRequest.getOrderItemList() || createOrderRequest.getOrderItemList().size() == 0
                || null == createOrderRequest.getWaiter()) {
            return "輸入錯誤";
        } else {
            List<OrderItem> list = new ArrayList<>();
            int price=0;
            for (OrderItem orderItem : createOrderRequest.getOrderItemList()) {
                if (null == orderItem.getMealName() || orderItem.getQuantity() <= 0) {
                    return "輸入錯誤";
                } else {
                    if (null == this.mealRepository.findByName(orderItem.getMealName())) {
                        return "找不到此餐點";
                    } else {
                        orderItem.setPrice(this.mealRepository.findByName(orderItem.getMealName()).getPrice());
                        //計算總價
                        price+=orderItem.getPrice()*orderItem.getQuantity();
                    //判斷是否重複輸入同品項
                        for (int i = 0; i < list.size(); i++) {
                            if (Objects.equals(orderItem.getMealName(), list.get(i).getMealName())) {
                                orderItem.setItemId(list.get(i).getItemId());
                                orderItem.setQuantity(orderItem.getQuantity() + list.get(i).getQuantity());
                                list.remove(list.get(i));
                            }
                        }
                        list.add(orderItem);
                    }
                }
            }
            order.setOrderItemList(list);
            order.setWaiter(createOrderRequest.getWaiter());
            order.setTotalPrice(price);
            this.orderRepository.save(order);
            return "OK";
        }
    }

    public String updateOrder(int id, UpdateOrderRequest updateOrderRequest) {
        Order order = this.orderRepository.findById(id);
        if (null == order) {
            return "order id輸入錯誤";
        } else {
            List<OrderItem> orderItemList = new ArrayList<>();

            //判斷是否修改服務生
            if (null != updateOrderRequest.getWaiter()) {
                order.setWaiter(updateOrderRequest.getWaiter());
            }

            //判斷是否修改餐點資訊
            if (null != updateOrderRequest.getOrderItemList()) {
                int price=0;
                for (int i = 0; i < updateOrderRequest.getOrderItemList().size(); i++) {
                    OrderItem orderItem = updateOrderRequest.getOrderItemList().get(i);
                    orderItem.setItemId(order.getOrderItemList().get(i).getItemId());

                    //判斷是否有修改餐點數量
                    if (orderItem.getQuantity() == 0) {
                        orderItem.setQuantity(order.getOrderItemList().get(i).getQuantity());
                    } else if (orderItem.getQuantity() < 0) {
                        return "輸入錯誤";
                    } else {
                        orderItem.setQuantity(orderItem.getQuantity());
                    }
                    //判斷是否有修改餐點名稱
                    if (null == orderItem.getMealName()) {
                        orderItem.setMealName(order.getOrderItemList().get(i).getMealName());
                        orderItem.setPrice(order.getOrderItemList().get(i).getPrice());
                    } else {
                        if (null == this.mealRepository.findByName(orderItem.getMealName())) {
                            return "找不到此餐點";
                        } else {
                            orderItem.setPrice(this.mealRepository.findByName(orderItem.getMealName()).getPrice());
                        }

                    }
                    price+=orderItem.getPrice()*orderItem.getQuantity();
                    orderItemList.add(orderItem);
                }
                order.setOrderItemList(orderItemList);
                order.setTotalPrice(price);
            }
        }
        this.orderRepository.save(order);
        return "OK";
    }

    public String deleteOrderById(int id) {
        Order order = this.orderRepository.findById(id);
        if (order == null) {
            return "order id輸入錯誤";
        } else {
            this.orderRepository.delete(order);
            return "ok";
        }
    }
}