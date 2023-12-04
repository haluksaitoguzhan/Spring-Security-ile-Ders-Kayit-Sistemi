package com.staj.staj.service.impl;

import com.staj.staj.dao.CourseDao;
import com.staj.staj.model.Course;
import com.staj.staj.service.IRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service(value = "courseService")
public class CourseServiceImpl implements IRepositoryService<Course> {
    @Autowired
    private CourseDao courseDao;
    @Override
    public Course add(Course entity) {
        return courseDao.save(entity);
    }

    @Override
    public List<Course> getAll() {
        List<Course> list = new ArrayList<>();
        courseDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Course getById(Long id) {
        return courseDao.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        Course course = courseDao.findById(id).orElse(null);
        if (course != null){
            courseDao.delete(course);
            return true;
        }
        return false;
    }

    @Override
    public Course update(Long id, Course entity) {
        Course course = courseDao.findById(id).orElse(null);
        if (course != null){
            course.setCourse_name(entity.getCourse_name());
            course.setDescription(entity.getDescription());
            courseDao.save(course);
        }
        return course;
    }
}
