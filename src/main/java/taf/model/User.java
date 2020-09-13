package taf.model;

import lombok.*;

@Data
public class User {
  String name;
  String job;

  public User(String name, String job) {
    this.name = name;
    this.job = job;
  }
}
