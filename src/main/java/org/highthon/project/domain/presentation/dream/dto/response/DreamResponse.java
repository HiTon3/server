package org.highthon.project.domain.presentation.dream.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.highthon.project.domain.entity.dream.types.Category;

@Getter
@AllArgsConstructor
@Builder
public class DreamResponse {
  private String image;
  private String video;
  private String text;
  private String dreamText;
  private Category category;
}
