package com.example.basic_poc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Collection;

@Entity
@ApiModel(description = "All details about the User. ")
public class User  {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  @ApiModelProperty(notes = "The database generated user ID")
  private Long id;

  @Column(name = "first_name")
  @ApiModelProperty(notes = "The User first name")
  private String firstName;

  @Column(name = "last_name")
  @ApiModelProperty(notes = "The User last name")
  private String lastName;

  @Column(name = "email")
  @ApiModelProperty(notes = "The User email")
  private String email;

  @Column(name = "password")
  @ApiModelProperty(notes = "The User password")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER,cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
  })
  @JoinTable(
          name = "users_roles",
          joinColumns = @JoinColumn(
                  name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(
                  name = "role_id", referencedColumnName = "id"))
  @ApiModelProperty(notes = "The User roles")
  private Collection<Role> roles;

  public User() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public Collection<Role> getRoles() {
    return roles;
  }

  public void setRoles(Collection<Role> roles) {

    this.roles = roles;
  }


}
