package com.example.code.controller;

import com.example.code.model.request.OrderRequest;
import com.example.code.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<Object> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<Object> createNewOrder(@RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }

}
