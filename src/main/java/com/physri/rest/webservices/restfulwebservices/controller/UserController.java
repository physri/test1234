package com.physri.rest.webservices.restfulwebservices.controller;

import com.physri.rest.webservices.restfulwebservices.dao.UserDaoService;
import com.physri.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.physri.rest.webservices.restfulwebservices.model.Post;
import com.physri.rest.webservices.restfulwebservices.model.User;
import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
  @Autowired UserDaoService userDaoService;
// (OR)
//  public UserController(UserDaoService userDaoService) {
//    this.userDaoService = userDaoService;
//  }

  @GetMapping("/users/all")
  public ResponseEntity findAllUsers() {
    return ResponseEntity.ok(userDaoService.findAllUsers());
  }

  @GetMapping("/users/id/{id}")
  public User findUserById(@PathVariable Integer id) {
    Optional<User> user = userDaoService.findById(id);
    if (user == null) {
      throw new UserNotFoundException("The requested user id doesn't exist!!!");
    }
    return user.get();
  }

  @PostMapping("/users/new")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User newUser = userDaoService.createUser(user);
/*    if(newUser == null){

    }*/
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(newUser.getId()).toUri();
    return ResponseEntity.created(location).build();
  }

  @DeleteMapping("/users/remove/{id}")
  public void removeUser(@PathVariable Integer id) {
    userDaoService.removeUserById(id);
  }

  @GetMapping("/users/posts/all")
  public ResponseEntity findAllPosts(){
    return ResponseEntity.ok(userDaoService.findAllPosts());
  }

  @PostMapping("users/posts/new/{id}")
  public ResponseEntity<Post> createPost(@PathVariable Integer id, @RequestBody Post post){
    Optional<User> user = userDaoService.findById(id);
    if (user == null) {
      throw new UserNotFoundException("The requested user id doesn't exist!!!");
    }
    post.setUser(user.get());
    Post newPost = userDaoService.createPost(post);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
      .buildAndExpand(newPost.getId()).toUri()).build();
  }

  @GetMapping("/users/posts/id/{id}")
  public Post findPostById(@PathVariable Integer id){
    Optional<Post> post = userDaoService.findPostById(id);
    if (post == null) {
      throw new UserNotFoundException("The requested post doesn't exist!!!");
    }
    return post.get();
  }
}