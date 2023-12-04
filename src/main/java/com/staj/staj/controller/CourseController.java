package com.staj.staj.controller;

import com.staj.staj.model.Course;
import com.staj.staj.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping("")
    public List<Course> getAllCourses(){return courseService.getAll();}

    @GetMapping("get/{id}")
    public Course getCourseById(@PathVariable Long id){return courseService.getById(id);}

    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course){return courseService.add(course);}

    @DeleteMapping("/delete/{id}")
    public Boolean deleteCourse(@PathVariable Long id){return courseService.delete(id);}

    @PutMapping("/update/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.update(id, course);
    }
}
