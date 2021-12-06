package com.example.code.service;

import com.example.code.model.Order;
import com.example.code.model.Status;
import com.example.code.model.request.OrderRequest;
import com.example.code.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<Object> getAllOrders(){
        try{
            List<Order> orders = orderRepository.findAll();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Object> createOrder(OrderRequest orderRequest) {
        Order newOrder = createOrderFromRequest(orderRequest);

        try{
            Order orderResponse = orderRepository.save(newOrder);
            emailService.sendEmail("den_is_star@ukr.net", orderResponse.toString());
            emailService.sendEmail(orderRequest.getEmail(), orderResponse.toString());
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        }catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public Order createOrderFromRequest(OrderRequest orderRequest){
        Order order = new Order();
        System.out.println(orderRequest.toString());
        order.setFirstName(orderRequest.getName());
        order.setSecondName(orderRequest.getLastName());
        order.setPhone(orderRequest.getPhone());
        order.setRegion(orderRequest.getRegion());
        order.setCity(orderRequest.getCity());
        order.setStreet(orderRequest.getStreet());
        order.setBuying_type(orderRequest.getBuyingType());
        order.setStatus(Status.ACTIVE.name());
        order.setComment(orderRequest.getDescription());
        return order;
    }

}
