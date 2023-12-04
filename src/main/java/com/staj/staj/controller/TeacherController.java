package com.staj.staj.controller;

import com.staj.staj.model.Course;
import com.staj.staj.model.User;
import com.staj.staj.model.UserDto;
import com.staj.staj.service.impl.CourseServiceImpl;
import com.staj.staj.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    CourseServiceImpl courseService;


    //@GetMapping("/{id}")
    //public Teacher getTeacherById(@PathVariable Long id){return teacherService.getById(id);}

    @PostMapping("/add")
    public User createUser(@RequestBody UserDto userDto){return userService.save(userDto);}

    @PostMapping("/delete/{id}")
    public Boolean deleteUser(@PathVariable Long id){return userService.delete(id);}

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @PostMapping("/student-{studentId}/mapped/course-{courseId}")
    public ResponseEntity<?> addStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        User user = userService.findOneById(studentId);
        Course course = courseService.getById(courseId);

        if (user != null && course != null) {
            if (user.getCourses().contains(course)) {
                // Öğrenci zaten bu dersi alıyor, bir hata mesajı döndürdüm
                String errorMessage = "Öğrenci bu dersi zaten alıyor.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
            }

            user.getCourses().add(course);
            userService.update(user.getId(), user);
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanıcı veya ders bulunamadı");
    }

    @PostMapping("/student-{studentId}/delete/course-{courseId}")
    public ResponseEntity<?> deleteStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        User user = userService.findOneById(studentId);
        Course course = courseService.getById(courseId);

        if (user != null && course != null && user.getCourses().contains(course)) {// Öğrenci bu dersi alıyor
            user.getCourses().remove(course);
            userService.update(user.getId(),user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanıcı veya ders bulunamadı");
    }

    
}
