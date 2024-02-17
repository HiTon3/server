package org.highthon.project.domain.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Entity(name ="user")
@Table
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  private String name;
  private String userName;

  @Builder
  public User(Long id, String email, String name, String userName) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.userName = userName;
  }
}
