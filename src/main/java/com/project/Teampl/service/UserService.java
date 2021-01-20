package com.project.Teampl.service;


import com.project.Teampl.model.user.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

//    User findById(Long useridx);

    void deleteById(Long useridx);

    User save(User user);

    void updateById(Long useridx, User user);

}
