package com.example.roleBased.dao;


import com.example.roleBased.entity.Role;
import com.example.roleBased.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role,String> {

}
