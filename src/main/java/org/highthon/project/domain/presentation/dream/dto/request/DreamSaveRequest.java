package org.highthon.project.domain.presentation.dream.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.highthon.project.domain.entity.dream.Dream;
import org.highthon.project.domain.entity.dream.types.Category;

import java.sql.Blob;

@Getter
@AllArgsConstructor
public class DreamSaveRequest {
  private String image;
  private String video;
  private String category;
  private String text;
  private String dreamText;
  private String userName;

  public Dream toEntity() {
    return Dream.builder()
      .image(image)
      .video(video)
      .category(Category.valueOf(category))
      .text(text)
      .dreamText(dreamText)
      .build();
  }
}
