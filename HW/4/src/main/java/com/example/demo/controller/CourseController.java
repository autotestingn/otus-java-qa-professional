package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/courses/")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @RequestMapping()
    public ModelAndView getCoursesView(Model model) {
        List<Course> courses = courseService.getAllCourses();
        Map<String, Object> params = new HashMap<>();
        params.put("courses", courses);
        return new ModelAndView("courses", params);
    }
}
