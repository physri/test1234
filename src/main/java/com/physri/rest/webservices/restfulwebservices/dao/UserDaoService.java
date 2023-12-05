package com.physri.rest.webservices.restfulwebservices.dao;

import com.physri.rest.webservices.restfulwebservices.model.Post;
import com.physri.rest.webservices.restfulwebservices.model.User;
import com.physri.rest.webservices.restfulwebservices.repository.PostRepo;
import com.physri.rest.webservices.restfulwebservices.repository.UserRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private PostRepo postRepo;

  public UserDaoService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  public List<User> findAllUsers() {
    return userRepo.findAll();
  }

  public Optional<User> findById(Integer id) {
    return userRepo.findById(id);
  }

  public User createUser(User user) {
    return userRepo.save(user);
  }

  public void removeUserById(Integer id) {
    userRepo.deleteById(id);
  }

  public List<Post> findAllPosts() {
    return postRepo.findAll();
  }

  public Post createPost(Post post) {
    return postRepo.save(post);
  }

  public Optional<Post> findPostById(Integer id) {
    return postRepo.findById(id);
  }
}