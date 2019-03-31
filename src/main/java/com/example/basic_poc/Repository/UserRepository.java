package com.example.basic_poc.Repository;

import com.example.basic_poc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

  User findByEmail(String email);
}
