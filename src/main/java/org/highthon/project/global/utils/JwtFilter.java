//package org.highthon.project.global.utils;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.highthon.project.domain.presentation.user.dto.CustomOAuth2User;
//import org.highthon.project.domain.presentation.user.dto.UserDto;
//import org.highthon.project.global.utils.jwt.JwtUtil;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@RequiredArgsConstructor
//public class JwtFilter extends OncePerRequestFilter {
//
//  private final JwtUtil jwtUtil;
//
//  @Value("${jwt.secret}")
//  private final String secret;
//
//  @Override
//  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//    //cookie들을 불러온 뒤 Authorization Key에 담긴 쿠키를 찾음
//    String authorization = null;
//    Cookie[] cookies = request.getCookies();
//    for (Cookie cookie : cookies) {
//
//      System.out.println(cookie.getName());
//      if (cookie.getName().equals("Authorization")) {
//
//        authorization = cookie.getValue();
//      }
//    }
//
//    //Authorization 헤더 검증
//    if (authorization == null) {
//
//      System.out.println("token null");
//      filterChain.doFilter(request, response);
//
//      //조건이 해당되면 메소드 종료 (필수)
//      return;
//    }
//
//    //토큰
//    String token = authorization;
//
//    //토큰 소멸 시간 검증
//    if (JwtUtil.isExpired(token, secret)) {
//
//      System.out.println("token expired");
//      filterChain.doFilter(request, response);
//
//      //조건이 해당되면 메소드 종료 (필수)
//      return;
//    }
//
//    //토큰에서 username과 role 획득
//    String username = jwtUtil.getUsername(token);
//
//    //userDTO를 생성하여 값 set
//    UserDto userDTO = new UserDto();
//    userDTO.setUserName(username);
//
//    //UserDetails에 회원 정보 객체 담기
//    CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);
//
//    //스프링 시큐리티 인증 토큰 생성
//    Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
//    //세션에 사용자 등록
//    SecurityContextHolder.getContext().setAuthentication(authToken);
//
//    filterChain.doFilter(request, response);
//  }
//}
//