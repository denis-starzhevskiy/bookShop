package com.example.code.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "statistics")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statisticsId;

    @Column(name = "created_date")
    @CreatedDate
    private Timestamp createdDate;

    @Version
    private short version;

    @Column(name = "views")
    private int views;

    public Statistics(short version, int views) {
        this.version = version;
        this.views = views;
    }

    public Statistics() {

    }

    public int getStatisticsId() {
        return statisticsId;
    }

    public void setStatisticsId(int statisticsId) {
        this.statisticsId = statisticsId;
    }

    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
