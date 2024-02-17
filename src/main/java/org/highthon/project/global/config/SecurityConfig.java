package org.highthon.project.global.config;

import lombok.RequiredArgsConstructor;
import org.highthon.project.domain.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final CustomOAuth2UserService oAuth2UserService;
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .cors(Customizer.withDefaults())
      .formLogin(AbstractHttpConfigurer::disable)
      .httpBasic(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(
        auth -> auth
          .requestMatchers("/api/**").permitAll()
          .anyRequest().authenticated()
      )
      .oauth2Login(
        oauth2 -> oauth2.userInfoEndpoint(
          userInfoEndpointConfig -> userInfoEndpointConfig.userService(oAuth2UserService)
        )
      )
      .sessionManagement(
        session -> session.sessionCreationPolicy(
          SessionCreationPolicy.STATELESS
        )
      );

    return http.build();
  }
}
