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

import com.java.c3s.dao.AdminDao;
import com.java.c3s.dao.CustomerDao;
import com.java.c3s.service.UsersDetailsService;

//@EnableJpaRepositories(basePackageClasses = CustomerDao.class)
@EnableJpaRepositories(basePackageClasses = {CustomerDao.class, AdminDao.class})
@EnableWebSecurity
@Configuration
// @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true,
// jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private UsersDetailsService userDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
    // Authentication auth1 =
    // SecurityContextHolder.getContext().getAuthentication();

  }
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {


    // httpSecurity.authorizeRequests().antMatchers("/admin/view").authenticated()
    // .anyRequest().hasRole("ADMIN").and().authorizeRequests().and()
    // .httpBasic();
    // .antMatchers("/customer/**").authenticated()
    // .anyRequest().hasRole("CUSTOMER").and().httpBasic();
    // Working code
    // httpSecurity.authorizeRequests().antMatchers("/customer/**").authenticated()
    // .anyRequest().hasRole("CUSTOMER").antMatchers("/admin/**")
    // .authenticated().anyRequest().hasAnyRole("ADMIN").and()
    // .authorizeRequests().and().httpBasic();

    // httpSecurity.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
    // .antMatchers("customer/**").hasRole("CUSTOMER").and().formLogin()
    // .loginPage("/login").usernameParameter("username")
    // .passwordParameter("userpassword").permitAll().and().logout()
    // .permitAll().and().csrf();
    // httpSecurity.authorizeRequests().antMatchers("/admin/**")
    // // .fullyAuthenticated().and().httpBasic();
    // .authenticated().anyRequest().hasRole("ADMIN").and().httpBasic();
    // httpSecurity.authorizeRequests().antMatchers("/customer/add").permitAll();

    // httpSecurity.authorizeRequests().antMatchers("/admin/view").permitAll();

    // httpSecurity.authorizeRequests().antMatchers("/customer/view").permitAll();
    // .formLogin()
    // .loginPage("/login").usernameParameter("username")
    // .passwordParameter("password").permitAll().and()
    // .logout().permitAll();

    httpSecurity.authorizeRequests().antMatchers("/customer/**").permitAll();
    httpSecurity.authorizeRequests().antMatchers("/admin/**").permitAll();
    httpSecurity.authorizeRequests().antMatchers("/booking/**").permitAll();
    httpSecurity.csrf().disable();
  }

  @Bean
  public BCryptPasswordEncoder encodePWD() {
    return new BCryptPasswordEncoder();
  }

  public SecurityConfiguration() {

  }

}
