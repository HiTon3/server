package org.highthon.project.domain.service.auth;

import lombok.RequiredArgsConstructor;
import org.highthon.project.domain.entity.user.User;
import org.highthon.project.domain.presentation.auth.dto.request.LoginRequest;
import org.highthon.project.domain.presentation.auth.dto.request.SignUpRequest;
import org.highthon.project.domain.presentation.auth.dto.response.LoginResponse;
import org.highthon.project.domain.repository.user.UserRepository;
import org.highthon.project.global.exception.ErrorCode.ErrorCode;
import org.highthon.project.global.exception.GlobalException;
import org.highthon.project.global.utils.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;

  @Value("${jwt.secret}")
  private String secretKey;

  private Long exprTime = 1000 * 60 * 60L;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@gmail.com";
  private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

  public static boolean validate(String email) {
    return pattern.matcher(email).matches();
  }

  @Transactional
  public void register(SignUpRequest dto) throws GlobalException {
    String email = dto.getEmail();
    boolean isEmailRight = validate(email);

    User isExist = userRepository.findByEmail(email);

    if (isExist != null) {
      throw new GlobalException(ErrorCode.USER_ALREADY_EXIST);
    }

    if (!isEmailRight || !Objects.equals(dto.getPassword(), dto.getRePassword())) {
      throw new GlobalException(ErrorCode.BAD_REQUEST_AUTH);
    }

    User user = dto.toEntity(bCryptPasswordEncoder);
    userRepository.save(user);
  }

  @Transactional
  public LoginResponse login(LoginRequest dto) {
    String email = dto.getEmail();
    System.out.println("email = " + email);
    String password = dto.getPassword();
    System.out.println("password = " + password);

    if (email == null) {
      throw new GlobalException(ErrorCode.BAD_REQUEST_AUTH);
    }

    System.out.println("check");

    User user = userRepository.findByEmail(email);
    System.out.println("user = " + user);

    System.out.println("user = " + user);
    if (user == null) {
      throw new GlobalException(ErrorCode.USER_NOT_FOUND);
    }
    System.out.println("check2");
    System.out.println("user.getUserName() = " + user.getUserName());

    boolean isPwMatch = bCryptPasswordEncoder.matches(password, user.getPassword());
    System.out.println("isPwMatch = " + isPwMatch);

    if (!isPwMatch) throw new GlobalException(ErrorCode.BAD_REQUEST_AUTH);

    String token = JwtUtil.createToken(email, secretKey, exprTime);

    System.out.println("token = " + token);
    return new LoginResponse(
      token,
      user.getUserName(),
      user.getId()
    );
  }
}
