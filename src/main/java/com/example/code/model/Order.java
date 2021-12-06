package com.example.code.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String secondName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "status")
    private String status;

    @Column(name = "buying_type")
    private String buying_type;

    @Column(name = "description")
    private String comment;

    @CreatedDate
    @Column(name = "created_date")
    private Timestamp createdDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "order_user",
    joinColumns = @JoinColumn(name = "user_user_id"),
    inverseJoinColumns = @JoinColumn(name = "order_user_id"))
    private Set<User> user;

}
