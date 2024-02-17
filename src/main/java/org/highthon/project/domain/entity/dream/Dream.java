package org.highthon.project.domain.entity.dream;

import jakarta.persistence.*;
import lombok.*;
import org.highthon.project.domain.entity.dream.types.Category;
import org.highthon.project.domain.entity.user.User;

import java.sql.Blob;

@Entity
@Table(name = "dream")
@Getter
//@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dream {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(columnDefinition = "LONGBLOB")
  private String image;
  @Column(columnDefinition = "LONGBLOB")
  private String video;
  @Enumerated(EnumType.STRING)
  private Category category;
  @Column(length = 500)
  private String text;
  private String dreamText;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User userId;
  public void setUserId(User userId) {
    this.userId = userId;
  }

  @Builder
  public Dream(Long id, String image, String video, Category category, String text, String dreamText, User userId) {
    this.id = id;
    this.image = image;
    this.video = video;
    this.category = category;
    this.text = text;
    this.dreamText = dreamText;
    this.userId = userId;
  }
}
