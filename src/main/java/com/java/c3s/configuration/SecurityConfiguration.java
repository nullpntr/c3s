package com.java.c3s.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.java.c3s.dao.CustomerDao;
import com.java.c3s.service.CustomerDetailsService;

@EnableJpaRepositories(basePackageClasses = CustomerDao.class)
@EnableWebSecurity
@Configuration

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private CustomerDetailsService userDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
  }
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.authorizeRequests().antMatchers("/customer/**").authenticated()
        .anyRequest().hasAnyRole("CUSTOMER").and().formLogin().permitAll();
    // httpSecurity.authorizeRequests().antMatchers("/customer/**").permitAll();
    httpSecurity.csrf().disable();
  }

  @Bean
  public BCryptPasswordEncoder encodePWD() {
    return new BCryptPasswordEncoder();
  }

  public SecurityConfiguration() {

  }

}
