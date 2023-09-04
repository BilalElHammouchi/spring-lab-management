package com.example.LabManagementApplication;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class LabSecurity extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http
        .authorizeRequests()
          .antMatchers("/register")
          .permitAll()
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
         .permitAll()
         .and()
      .logout()
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