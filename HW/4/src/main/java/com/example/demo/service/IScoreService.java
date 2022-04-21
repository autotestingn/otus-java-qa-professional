package com.example.demo.service;

import com.example.demo.model.UserScore;

import java.util.List;

public interface IScoreService {
    List<UserScore> findByUserId(Long userId);
}
