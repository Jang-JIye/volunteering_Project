package com.team8.volunteerworkproject.config;

import com.team8.volunteerworkproject.jwt.JwtAuthFilter;
import com.team8.volunteerworkproject.jwt.JwtUtil;
import com.team8.volunteerworkproject.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

  private final JwtUtil jwtUtil;

  private final UserDetailsServiceImpl userDetailsService;


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();}

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring()
        .requestMatchers(PathRequest.toH2Console())
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable();

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.authorizeHttpRequests().requestMatchers("/users/signup").permitAll()
        .requestMatchers("/users/signin").permitAll()
        .requestMatchers("/admin/signup").permitAll()
        .requestMatchers(HttpMethod.GET,"/posts/**").permitAll()
        .and().addFilterBefore(new JwtAuthFilter(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);

    http.formLogin().disable();


    return http.build();
    //깃허브 테스트용 커밋
  }
}