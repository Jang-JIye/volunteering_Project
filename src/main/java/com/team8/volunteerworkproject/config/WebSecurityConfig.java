package com.team8.volunteerworkproject.config;

import com.team8.volunteerworkproject.jwt.JwtAuthFilter;
import com.team8.volunteerworkproject.jwt.JwtUtil;
import com.team8.volunteerworkproject.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig implements WebMvcConfigurer {

  private final JwtUtil jwtUtil;

  private final UserDetailsServiceImpl userDetailsService;

  public static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH";


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring()
        .requestMatchers(PathRequest.toH2Console())
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().authorizeHttpRequests()
        .requestMatchers(HttpMethod.OPTIONS).permitAll()
        .requestMatchers("/users/signup").permitAll()
        .requestMatchers("/users/signin").permitAll()
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .requestMatchers("/challenges/**").permitAll()
        .requestMatchers("/volunteerWorkPosts/**").permitAll()
        .requestMatchers("/notices/**").permitAll()
        .requestMatchers("/profileImage/**").hasAnyRole("USER", "COMPANY", "ADMIN")
        .requestMatchers("/mypage/**").hasAnyRole("USER", "COMPANY", "ADMIN")
        .requestMatchers("/challenge-auth/**").permitAll()
        .anyRequest().authenticated()
        .and().addFilterBefore(new JwtAuthFilter(jwtUtil, userDetailsService),
            UsernamePasswordAuthenticationFilter.class);

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.formLogin().disable();

    return http.build();

  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .exposedHeaders("Authorization")
        .allowedMethods(ALLOWED_METHOD_NAMES.split(","));
  }
}