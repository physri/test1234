package com.physri.rest.webservices.restfulwebservices.repository;

import com.physri.rest.webservices.restfulwebservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}