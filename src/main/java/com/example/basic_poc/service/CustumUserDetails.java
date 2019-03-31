package com.example.basic_poc.service;

import com.example.basic_poc.Repository.RolesRepository;
import com.example.basic_poc.Repository.UserRepository;
import com.example.basic_poc.model.Privilege;
import com.example.basic_poc.model.Role;
import com.example.basic_poc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustumUserDetails implements UserDetailsService {


  private final UserRepository userRepository;
  private final RolesRepository rolesRepository;

  @Autowired
  public CustumUserDetails(final UserRepository userRepository,
                           final RolesRepository rolesRepository) {
    super();
    this.userRepository = userRepository;
    this.rolesRepository = rolesRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = this.userRepository.findByEmail(email);

    return new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(), true, true, true,
            true, getAuthorities(user.getRoles()));

  }

  private Collection<? extends GrantedAuthority> getAuthorities(
          Collection<Role> roles) {

    return getGrantedAuthorities(getPrivileges(roles));
  }

  private List<String> getPrivileges(Collection<Role> roles) {

    List<String> privileges = new ArrayList<>();
    List<Privilege> collection = new ArrayList<>();
    for (Role role : roles) {
      collection.addAll(role.getPrivileges());
    }
    for (Privilege item : collection) {
      privileges.add(item.getName());
    }
    return privileges;
  }


  private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String privilege : privileges) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
  }
}
