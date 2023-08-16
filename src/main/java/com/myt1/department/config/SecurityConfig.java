package com.myt1.department.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/api/v1/adddepartment").authenticated()
                .antMatchers("/api/v1/update/{depId}").authenticated()
                .antMatchers("/api/v1/getdepartmentbyid/{id}").authenticated()
                .antMatchers("/api/v1/getalldepartment").authenticated()
                .antMatchers("/api/v1/deleteDepartmentById/{id}").authenticated()
                .antMatchers("/api/v1/pagingAndSortingDepartment/{pageNumber}/{pageSize}").permitAll()
                .and().httpBasic();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("akanksha").password("Aunika@2311").authorities("admin").and()
                .withUser("ranjan").password("ranjan@123").authorities("read").and()
                .withUser("aadil").password("aadil@123").authorities("read").and()
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}

