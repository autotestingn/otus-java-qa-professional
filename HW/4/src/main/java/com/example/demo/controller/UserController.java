package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.model.UserScore;
import com.example.demo.service.IScoreService;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users/")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IScoreService scoreService;

    @RequestMapping()
    public ModelAndView getUsersView(Model model) {
        List<User> users =  userService.getAllUsers();
        Map<String, Object> params = new HashMap<>();
        params.put("users", users);
        return new ModelAndView("users", params);
    }

    @RequestMapping(value = "/{id}/scores/")
    public ModelAndView getUsersScoresView(Model model, @PathVariable("id") long userId) {
        List<UserScore> userScores = scoreService.findByUserId(userId);
        Map<String, Object> params = new HashMap<>();
        params.put("scores", userScores);
        return new ModelAndView("scores", params);
    }
}
