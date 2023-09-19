package com.example.LabManagementApplication.security;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.LabManagementApplication.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class LabSecurity extends WebSecurityConfigurerAdapter {

  @Autowired
  
  @Bean
  public CustomUserDetailsService userDetailsService() {
      return new CustomUserDetailsService();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());
        
      return authProvider;
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http
        .authorizeRequests()
          .antMatchers("/register")
          .permitAll()
          .and()
        .authorizeRequests()
          .antMatchers("/membersManagement")
          .hasRole("admin")
          .and()
        .authorizeRequests()
          .antMatchers("/projectManagement")
          .hasRole("admin")
          .and()
        .authorizeRequests()
          .antMatchers("/publicationManagement")
          .hasRole("admin")
          .and()
        .authorizeRequests()
          .antMatchers("/resourceManagement")
          .hasRole("admin")
          .and()
        .authorizeRequests()
          .antMatchers("/create-user")
          .permitAll()
          .and()
        .authorizeRequests()
          .antMatchers("/index")
          .authenticated()
          .and()
        .authorizeRequests()
          .antMatchers("/")
          .authenticated()
          .and()
       .formLogin()
         .loginPage("/login")
         .usernameParameter("email")
         .defaultSuccessUrl("/index")
         .permitAll()
         .and()
      .logout()
        .logoutSuccessUrl("/login")
        .permitAll()
        .and()
      .httpBasic();
      http.cors().and().csrf().disable();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(Arrays.asList("*"));
      configuration.setAllowedMethods(Arrays.asList("*"));
      configuration.setAllowedHeaders(Arrays.asList("*"));
      configuration.setAllowCredentials(true);
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
  }
}