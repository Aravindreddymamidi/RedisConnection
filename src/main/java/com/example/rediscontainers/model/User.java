package com.example.rediscontainers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
//@Data
@Getter
public class User {
  private String id;
  private String name;
  // getters and setters


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id.equals(user.id) && name.equals(user.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
