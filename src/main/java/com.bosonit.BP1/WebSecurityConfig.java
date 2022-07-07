package com.bosonit.BP1;

import com.bosonit.BP1.application.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.GET, "/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        return http.build();
    }

}
