package com.example.code.service;

import com.example.code.model.Author;
import com.example.code.model.Statistics;
import com.example.code.repository.StatisticsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StatisticsService {

    private Logger logger = LoggerFactory.getLogger(StatisticsService.class);

    @Autowired
    private StatisticsRepository statisticsRepository;

    public ResponseEntity<Object> getStatisticsById(Long id){
        try {
            Optional<Statistics> statistics = statisticsRepository.findById(id);
            return new ResponseEntity<>(statistics, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getStatistics() {
        try {
            List<Statistics> statistics = statisticsRepository.findAll();
            return new ResponseEntity<>(statistics, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> updateStatistics(Statistics statistics) {
        try{
            Statistics statisticsResult = statisticsRepository.findById(statistics.getId()).orElseThrow(() -> new NoSuchElementException("Author doesn't exist"));
            statisticsResult.setViews(statistics.getViews());
            statisticsResult.setVersion(statistics.getVersion());
            Statistics statisticsSave = statisticsRepository.save(statisticsResult);
            return new ResponseEntity<>(statisticsSave, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
