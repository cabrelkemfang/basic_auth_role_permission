package com.example.basic_poc.Repository;

import com.example.basic_poc.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
