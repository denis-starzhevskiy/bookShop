package com.example.code.controller;

import com.example.code.dto.SubCategoryDto;
import com.example.code.model.Statistics;
import com.example.code.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<Object> getStatistics(){
        return statisticsService.getStatistics();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStatisticById(@PathVariable Long id){
        return statisticsService.getStatisticsById(id);
    }

    @PutMapping
    public ResponseEntity<Object> updateSubCategory(@RequestBody Statistics statistics){
        return statisticsService.updateStatistics(statistics);
    }
}
