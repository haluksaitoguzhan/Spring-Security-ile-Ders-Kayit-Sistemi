package com.staj.staj.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.staj.staj.dao.UserDao;
import com.staj.staj.model.Course;
import com.staj.staj.model.Role;
import com.staj.staj.model.User;
import com.staj.staj.model.UserDto;
import com.staj.staj.service.RoleService;
import com.staj.staj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String no) throws UsernameNotFoundException {
        User user = userDao.findUsersByNo(no);
        if(user == null){
            throw new UsernameNotFoundException("Invalid mail or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getNo(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Role teacherRole = roleService.findByName("TEACHER");//Eğer öğretmen rolü içeriyorsa listeye eklenmesini istemiyorum
        for (User user : userDao.findAll()){
            if (!user.getRoles().contains(teacherRole)){
                list.add(user);
            }
        }
        return list;
    }

    @Override
    public User findOneById(Long id) {
        if (userDao.findById(id).orElse(null).getRoles().contains(roleService.findByName("TEACHER"))){
            return null;
        }
        return userDao.findById(id).orElse(null);
    }

    @Override
    public User save(UserDto user) {

        User nUser = user.getUserFromDto();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        Role role = roleService.findByName("STUDENT");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        if(nUser.getEmail().split("@")[1].equals("admin.edu")){
            role = roleService.findByName("TEACHER");
            roleSet.add(role);
        }

        nUser.setRoles(roleSet);
        return userDao.save(nUser);
    }

    public User update(Long id, User entity) {
        User user = userDao.findById(id).orElse(null);
        if (user != null){
            user.setName(entity.getName());
            user.setEmail(entity.getEmail());
            user.setPassword(entity.getPassword());
            user.setRoles(entity.getRoles());
            user.setNo(entity.getNo());
            user.setCourses(entity.getCourses());
            userDao.save(user);
        }
        return user;
    }

    public boolean delete(Long id){
        Role teacherRole = roleService.findByName("TEACHER");//Eğer öğretmen rolü içeriyorsa silinmesini istemiyorum
        for (User user : userDao.findAll()){
            if (!user.getRoles().contains(teacherRole)){
                userDao.delete(user);
                return true;
            }
        }
        return false;
    }
}
