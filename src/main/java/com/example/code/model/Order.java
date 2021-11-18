package com.example.code.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "order")
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

    @Column(name = "location")
    private String location;

    @Column(name = "section")
    private String section;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;

    @Column(name = "buying_type")
    private String buying_type;

    @Column(name = "paying_type")
    private String paying_type;

    @Column(name = "comment")
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
