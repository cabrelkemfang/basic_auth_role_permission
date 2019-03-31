package com.example.basic_poc.controller;

import com.example.basic_poc.Repository.PrivilegeRepository;
import com.example.basic_poc.Repository.RolesRepository;
import com.example.basic_poc.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

  private final RolesRepository rolesRepository;
  private final PrivilegeRepository privilegeRepository;

  @Autowired
  public RoleController(final RolesRepository rolesRepository,
                        final PrivilegeRepository privilegeRepository) {
    super();
    this.rolesRepository = rolesRepository;
    this.privilegeRepository = privilegeRepository;
  }

  @RequestMapping(value = "/role", method = RequestMethod.GET)
  public ResponseEntity<List<Role>> getRole() {
    List<Role> roleList;
    roleList = this.rolesRepository.findAll();
    return new ResponseEntity<>(roleList, HttpStatus.OK);
  }

  @RequestMapping(value = "/role", method = RequestMethod.POST)
  public Role createRole(@RequestBody Role role) {

    Role role1 = this.rolesRepository.save(role);
    return role1;
  }



}
