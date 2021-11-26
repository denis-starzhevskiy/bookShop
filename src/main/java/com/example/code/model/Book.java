package com.example.code.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "book")
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "description")
    private String description;

    @Column(name = "page_amount")
    private int pageAmount;

    @Column(name = "price")
    private short price;

    @Column(name = "photo_name")
    private String photoName;

    @Column(name = "isbn")
    private int ISBN;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "language")
    private Languages language;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "statistics_id")
    private Statistics statistics;

//    @JsonIgnore
    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private Set<SubCategory> subcategory;

    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private Set<Cart> cart;

    @Transient
    private byte[] photoData;

    @JsonIgnore
    public String getPhotoName() {
        return photoName;
    }
}
