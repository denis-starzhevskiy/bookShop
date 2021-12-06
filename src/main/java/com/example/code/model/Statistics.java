package com.example.code.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "statistics")
@Data
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistics_id")
    private Long id;

    @Column(name = "created_date")
    @CreatedDate
    private Timestamp createdDate;

    @Version
    private short version;

    @Column(name = "views")
    private int views;

    @Column(name = "book_id")
    private Long book_id;

    public Statistics(Timestamp createdDate, short version, int views) {
        this.createdDate = createdDate;
        this.version = version;
        this.views = views;
    }

    public Statistics(){}
}
