package com.example.basic_poc.controller;

import com.example.basic_poc.Repository.RolesRepository;
import com.example.basic_poc.Repository.UserRepository;
import com.example.basic_poc.model.Role;
import com.example.basic_poc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class UserControler {

  private final UserRepository userRepository;
  private final RolesRepository rolesRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public UserControler(final UserRepository userRepository,
                       final RolesRepository rolesRepository,
                       final BCryptPasswordEncoder bCryptPasswordEncoder

  ) {
    super();
    this.userRepository = userRepository;
    this.rolesRepository = rolesRepository;
    this.bCryptPasswordEncoder=bCryptPasswordEncoder;
  }

  @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
  @RequestMapping(value = "/user", method = RequestMethod.GET)
  public ResponseEntity<List<User>> getAllUser() {
    List<User> userList;

    userList = this.userRepository.findAll();
    return new ResponseEntity<>(userList, HttpStatus.OK);
  }

  @RequestMapping(value = "/user/{roleName}", method = RequestMethod.POST)
  public User createUser(@RequestBody User user, @PathVariable("roleName") String roleName) {

    User user1 = new User();
    user1.setEmail(user.getEmail());
    user1.setFirstName(user.getFirstName());
    user1.setLastName(user.getLastName());
    user1.setRoles(Arrays.asList(this.rolesRepository.findByName(roleName)));
    user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user1.setPassword(user.getPassword());
    this.userRepository.save(user);

    return user;
  }

  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public User createUser1(@RequestBody User user) {

    User user1 = new User();
    user1.setEmail(user.getEmail());
    user1.setFirstName(user.getFirstName());
    user1.setLastName(user.getLastName());
    user1.setRoles(user.getRoles());
    user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    this.userRepository.save(user);

    return user;
  }

}
