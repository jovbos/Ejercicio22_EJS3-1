package com.bosonit.BP1.infracstructure.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bosonit.BP1.application.ports.person.GetPersonPort;
import com.bosonit.BP1.domain.entities.Person;
import com.bosonit.BP1.domain.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.ws.rs.NotFoundException;

@RestController
public class UserController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    GetPersonPort getPersonPort;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) throws NotFoundException {

        if (personRepository.findByUsername(username).isEmpty()) throw new NotFoundException("USER NOT FOUND");
        Person user = personRepository.findByUsername(username).get(0);
        if (!user.getPassword().equals(password)) throw new NotFoundException("WRONG PASSWORD");
        String rol;
        if (user.getAdmin()) {
            rol = "ROLE_ADMIN";
        } else {
            rol = "ROLE_USER";
        }
        return getJWTToken(username, rol);
    }

    private String getJWTToken(String username, String rol) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(rol);

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}