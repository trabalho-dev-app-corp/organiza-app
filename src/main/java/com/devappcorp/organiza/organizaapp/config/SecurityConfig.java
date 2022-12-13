package com.devappcorp.organiza.organizaapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.devappcorp.organiza.organizaapp.domain.model.Role;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/usuarios/authenticate", "/usuarios/login").permitAll()
                .antMatchers("/eventos/**").hasRole(Role.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails annaSmith = User.builder()  
            .username("anna")
            .password(passwordEncoder.encode("girl"))
            .roles(Role.USUARIO.name())
            .build();

        UserDetails linda = User.builder()
        .username("linda")
        .password(passwordEncoder.encode("girl"))
        .roles(Role.ADMIN.name())
        .build();

        return new InMemoryUserDetailsManager(
            annaSmith,
            linda
        );
    }

}
