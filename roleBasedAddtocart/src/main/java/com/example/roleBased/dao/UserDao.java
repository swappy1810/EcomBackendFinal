package com.example.roleBased.dao;


import com.example.roleBased.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

     Optional<User> getUserByUsername(String username);
     Optional<User> getUserByEmail(String email);
     User findByEmail(String email);
     Optional<User> findOneByEmailAndPassword(String email,String password);


}
