package org.highthon.project.domain.presentation.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.highthon.project.domain.entity.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@AllArgsConstructor
public class SignUpRequest {
  @NotBlank(message = "이메일은 필수값입니다")
  private String email;
  @NotBlank(message = "")
  private String password;
  private String rePassword;
  private String userName;

  public User toEntity(BCryptPasswordEncoder bCryptPasswordEncoder) {
    return User.builder()
      .email(email)
      .password(bCryptPasswordEncoder.encode(password))
      .userName(userName)
      .role("ROLE_USER")
      .build();
  }
}
