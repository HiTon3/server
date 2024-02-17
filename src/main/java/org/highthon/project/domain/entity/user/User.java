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
  @Setter
  private String email;
  @Setter
  private String name;
  private String userName;
  private String role;

  @Builder
  public User(Long id, String email, String name, String userName, String role) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.userName = userName;
    this.role = role;
  }
}
