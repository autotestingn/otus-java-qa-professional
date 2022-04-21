package com.example.demo.rest;

import com.example.demo.model.UserScore;
import com.example.demo.service.IScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ScoreRestController {
    @Autowired
    private IScoreService scoreService;
    @GetMapping("/api/users/{userId}/scores/")
    public ResponseEntity<List<UserScore>> getAllScoresByUserId(@PathVariable(value = "userId") Long userId) {
        List<UserScore> scores = scoreService.findByUserId(userId);
        if (scores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(scores, HttpStatus.OK);
    }
}
