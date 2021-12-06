package com.example.code.controller;

import com.example.code.model.request.UserRequest;
import com.example.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> getUserInformation(@PathVariable("username") String username){
        return userService.getUserInformation(username);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserRequest userRequest) { return userService.createUser(userRequest);}

}
