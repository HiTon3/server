package org.highthon.project.domain.entity.user;

import jakarta.persistence.*;
import lombok.*;
import org.highthon.project.domain.entity.dream.Dream;

import java.util.List;

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
  private String role;
  @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true /** User가 삭제되면 Dream 객체도 삭제*/)
  private List<Dream> dreamList;
  public void addDream(Dream dream) {
    dream.setUserId(this);
    this.dreamList.add(dream);
  }

  @Builder
  public User(Long id, String email, String name, String userName, String role) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.userName = userName;
    this.role = role;
  }
}
