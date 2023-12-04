package com.staj.staj.dao;

import com.staj.staj.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseDao extends CrudRepository<Course, Long> {

}
