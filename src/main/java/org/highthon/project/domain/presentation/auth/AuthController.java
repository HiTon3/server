package org.highthon.project.domain.presentation.auth;

import lombok.RequiredArgsConstructor;
import org.highthon.project.domain.presentation.auth.dto.request.LoginRequest;
import org.highthon.project.domain.presentation.auth.dto.request.SignUpRequest;
import org.highthon.project.domain.presentation.auth.dto.response.LoginResponse;
import org.highthon.project.domain.service.auth.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/signUp")
  public void signUp(@RequestBody SignUpRequest dto) {
    System.out.println("dto = " + dto);
    authService.register(dto);
  }

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest dto) {
    System.out.println("dto = " + dto);
    LoginResponse result = authService.login(dto);
    return result;
  }
}
