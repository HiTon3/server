package org.highthon.project.domain.presentation.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
  private String token;
  private String userName;
  private Long userId;
}
