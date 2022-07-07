package com.bosonit.BP1;

import com.bosonit.BP1.application.JWTAuthorizationFilter;
import com.bosonit.BP1.domain.entities.Person;
import com.bosonit.BP1.domain.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;

@SpringBootApplication
public class Main {

	@Autowired
	PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@PostConstruct
	public void exe() {
		Person defaultAdmin = new Person(1, "admin", "123", "pepe", "aaa", "aaa@", "eee@", "ciutat", true, null, null, null, true, null, null);
		personRepository.save(defaultAdmin);
		Person defaultUser = new Person(2, "user", "456", "pepe", "aaa", "aaa@", "eee@", "ciutat", true, null, null, null, false, null, null);
		personRepository.save(defaultUser);
	}


}
