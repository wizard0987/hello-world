package com.project.Teampl.repository;


import com.project.Teampl.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserid(String userid);
}
