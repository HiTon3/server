package org.highthon.project.domain.entity.dream;

import jakarta.persistence.*;
import lombok.*;
import org.highthon.project.domain.entity.dream.types.Category;
import org.highthon.project.domain.entity.user.User;

import java.sql.Blob;

@Entity
@Table
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dream {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Lob
  private byte[] image;
  @Lob
  private Blob video;
  @Enumerated(EnumType.STRING)
  private Category category;
  private String text;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User userId;

  @Builder
  public Dream(Long id, byte[] image, Blob video, Category category, String text, User userId) {
    this.id = id;
    this.image = image;
    this.video = video;
    this.category = category;
    this.text = text;
    this.userId = userId;
  }
}
