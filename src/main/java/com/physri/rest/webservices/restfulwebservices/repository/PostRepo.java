package com.physri.rest.webservices.restfulwebservices.repository;

import com.physri.rest.webservices.restfulwebservices.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {

}
