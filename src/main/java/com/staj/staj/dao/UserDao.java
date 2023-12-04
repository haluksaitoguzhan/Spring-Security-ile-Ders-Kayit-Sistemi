package com.staj.staj.dao;

import com.staj.staj.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findUsersByNo(String no);
}