package com.staj.staj.controller;

import com.staj.staj.model.Course;
import com.staj.staj.model.User;
import com.staj.staj.model.UserDto;
import com.staj.staj.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("")
    public List<User> getAllStudents(){return userService.findAll();}

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){return userService.findOneById(id);}

}
