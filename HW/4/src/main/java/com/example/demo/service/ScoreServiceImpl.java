package com.example.demo.service;

import com.example.demo.entity.Score;
import com.example.demo.model.UserScore;
import com.example.demo.repository.IScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements IScoreService {
    @Autowired
    IScoreRepository scoreRepository;

    @Override
    public List<UserScore> findByUserId(Long userId) {
        List<UserScore> userScoreList = new ArrayList<>();
        List<Score> scores = scoreRepository.findAll();
        List<Score> scoresById = scores.stream().filter(s -> s.getUser().getId().equals(userId)).collect(Collectors.toList());

        for (Score score : scoresById) {
            UserScore userScore = new UserScore();
            userScore.setScore(score.getScore());
            userScore.setUserName(score.getUser().getName());
            userScore.setCourseName(score.getCourse().getName());
            userScoreList.add(userScore);
        }
        return userScoreList;
    }
}
