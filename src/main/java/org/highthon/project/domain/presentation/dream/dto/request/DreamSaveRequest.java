package org.highthon.project.domain.presentation.dream.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.highthon.project.domain.entity.dream.Dream;
import org.highthon.project.domain.entity.dream.types.Category;

@Getter
@AllArgsConstructor
public class DreamSaveRequest {
  private String image;
  private String video;
  private String category;
  private String text;
  private String userName;

  public Dream toEntity() {
    return Dream.builder()
      .image(image)
      .video(video)
      .category(Category.valueOf(category))
      .text(text)
      .build();
  }
}
