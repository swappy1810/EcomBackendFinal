package com.example.roleBased.serviceImpl;

import com.example.roleBased.config.JwtUtil;
import com.example.roleBased.dao.RoleDao;
import com.example.roleBased.dao.UserDao;
import com.example.roleBased.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Autowired
   private JwtUtil jwtUtil;

    //method to register as new user
    public User registerNewUser(User user) {
        Optional<User> usernameEntry = userDao.getUserByUsername(user.getUsername());
        Optional<User> emailEntry = userDao.getUserByEmail(user.getEmail());

        //check weather username is already present or not
        if(usernameEntry.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }
        //check weather email is already present or not
        if(emailEntry.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }
        else{
            userDao.save(user);
            System.out.println("New user registered!");
        }
        Role role = roleDao.findById("user").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setCart(new Cart());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    //method to initialize roles to user or admin
    public void initRolesAndUsers() {
        Role adminRole = new Role();
        adminRole.setRole_name("Admin");
        adminRole.setRoleDesc("Admin Role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRole_name("User");
        userRole.setRoleDesc("default user role");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserId(1);
        adminUser.setUsername("Admin");
        adminUser.setEmail("admin123@gmail.com");
        adminUser.setPassword(passwordEncoder.encode("Admin@123"));

        Set<Role> adminRoles = new HashSet<>();
        adminUser.setRoles(adminRoles);
        adminRoles.add(adminRole);
        userDao.save(adminUser);

    }

    //method to find user by username
    public Optional<User> findByUsername(String username) {
        return userDao.getUserByUsername(username);
    }


}

