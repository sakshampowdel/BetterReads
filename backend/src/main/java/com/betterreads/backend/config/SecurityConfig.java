package com.betterreads.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors(withDefaults())
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            // --- Authenticated endpoints ---
            .requestMatchers("/api/users/me").authenticated()
            .requestMatchers(HttpMethod.POST, "/api/reviews", "/api/booklists/**").authenticated()
            .requestMatchers(HttpMethod.DELETE, "/api/booklists/**").authenticated()

            // --- Public endpoints ---
            .requestMatchers(
                "/api/users/register",
                "/api/users/login")
            .permitAll()
            .requestMatchers(HttpMethod.GET,
                "/api/books/**",
                "/api/authors/**",
                "/api/reviews/book/**",
                "/api/profiles/**",
                "/api/booklists/**")
            .permitAll()
            .requestMatchers(HttpMethod.POST, "/api/books/**", "/api/authors/**")
            .permitAll()

            // --- Default: secure everything else ---
            .anyRequest().authenticated())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .formLogin(form -> form.disable())
        .httpBasic(basic -> basic.disable());

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
}
