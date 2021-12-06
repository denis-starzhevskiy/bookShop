package com.example.code.model.request;

import lombok.Data;

@Data
public class OrderRequest {

    String name;
    String lastName;
    String email;
    String phone;
    String region;
    String city;
    String street;
    String buyingType;
    String description;

}
