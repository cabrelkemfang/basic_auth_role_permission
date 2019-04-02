package com.example.basic_poc.controller;

import com.example.basic_poc.Repository.RolesRepository;
import com.example.basic_poc.Repository.UserRepository;
import com.example.basic_poc.model.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@Api(value = "User", description = "Operations on the user")
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
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
  @ApiOperation(value = "View a list of user", response = List.class, consumes = "application/json")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved list"),
          @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
          @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  @RequestMapping(value = "/user", method = RequestMethod.GET)
  public ResponseEntity<List<User>> getAllUser() {
    List<User> userList;

    userList = this.userRepository.findAll();
    return new ResponseEntity<>(userList, HttpStatus.OK);
  }

  @ApiOperation(value = "Add an User 1")
  @RequestMapping(value = "/user/{roleName}", method = RequestMethod.POST)
  public User createUser(@ApiParam(value = "user object store in database table", required = true) @RequestBody User user, @PathVariable("roleName") String roleName) {

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

  @ApiOperation(value = "Add an User")
  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public User createUser1(@ApiParam(value = "User object store in the db", required = true) @RequestBody User user) {

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
