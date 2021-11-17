package com.example.code.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cart_book")
public class CartBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartBookId;

    @Column(name = "qty")
    private short qty;

    @Column(name = "final_price")
    private short final_price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public int getCartBookId() {
        return cartBookId;
    }

    public void setCartBookId(int cartBookId) {
        this.cartBookId = cartBookId;
    }

    public short getQty() {
        return qty;
    }

    public void setQty(short qty) {
        this.qty = qty;
    }

    public short getFinal_price() {
        return final_price;
    }

    public void setFinal_price(short final_price) {
        this.final_price = final_price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
