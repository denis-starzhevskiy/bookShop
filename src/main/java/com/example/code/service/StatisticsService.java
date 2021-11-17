package com.example.code.service;

import com.example.code.model.Statistics;
import com.example.code.repository.StatisticsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class StatisticsService {

    private Logger logger = LoggerFactory.getLogger(StatisticsService.class);

    @Autowired
    private StatisticsRepository statisticsRepository;

    public ResponseEntity<Object> getStatisticsById(int id){
        try {
            Optional<Statistics> statistics = statisticsRepository.findById(id);
            return new ResponseEntity<>(statistics, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
