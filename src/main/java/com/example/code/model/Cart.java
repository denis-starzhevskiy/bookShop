package com.example.code.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @Column(name = "total_products")
    private short total_products;

    @Column(name = "final_price")
    private BigDecimal final_price;

    @Column(name = "in_order")
    private boolean in_order;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<Order> order;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartBook> cartBooks;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "book_cart",
            joinColumns = @JoinColumn(name = "book_cart_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_books"))
    private Set<Book> books;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public short getTotal_products() {
        return total_products;
    }

    public void setTotal_products(short total_products) {
        this.total_products = total_products;
    }

    public BigDecimal getFinal_price() {
        return final_price;
    }

    public void setFinal_price(BigDecimal final_price) {
        this.final_price = final_price;
    }

    public boolean isIn_order() {
        return in_order;
    }

    public void setIn_order(boolean in_order) {
        this.in_order = in_order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
