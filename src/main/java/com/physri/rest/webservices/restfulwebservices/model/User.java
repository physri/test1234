package com.physri.rest.webservices.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "user_details")
public class User {

  public User() {
  }

  @Id
  @GeneratedValue
  @Column
  private Integer id;
  @Column(name = "USERNAME")
  private String userName;
  @Column(name = "BIRTHDATE")
  private LocalDate birthDate;
  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<Post> posts;

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", userName='" + userName + '\'' + ", birthDate=" + birthDate
        + '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }
  public User(Integer id, String userName, LocalDate birthDate) {
    this.id = id;
    this.userName = userName;
    this.birthDate = birthDate;
  }
}
