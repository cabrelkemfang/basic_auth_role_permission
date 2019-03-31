package com.example.basic_poc.Repository;

import com.example.basic_poc.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {

  Privilege findByName(String name);
}
