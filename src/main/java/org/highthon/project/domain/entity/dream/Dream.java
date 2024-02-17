package org.highthon.project.domain.entity.dream;

import jakarta.persistence.*;
import lombok.*;
import org.highthon.project.domain.entity.dream.types.Category;
import org.highthon.project.domain.entity.user.User;

@Entity
@Table(name = "dream")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dream {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Lob
  private String image;
  @Lob
  private String video;
  @Enumerated(EnumType.STRING)
  private Category category;
  private String text;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User userId;
  public void setUserId(User userId) {
    this.userId = userId;
  }

  @Builder
  public Dream(Long id, String image, String video, Category category, String text, User userId) {
    this.id = id;
    this.image = image;
    this.video = video;
    this.category = category;
    this.text = text;
    this.userId = userId;
  }
}
