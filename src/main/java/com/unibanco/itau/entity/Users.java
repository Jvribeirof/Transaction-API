package com.unibanco.itau.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private List<String> authority;

    @OneToOne(mappedBy = "users",cascade = CascadeType.ALL)
    private ApiKey key;

    public Users(String username, String password, List<String> authority, ApiKey key) {
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.key = key;
    }
    public Users(){}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authority.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.username;
    }
    public ApiKey getKey(){
        return this.key;
    }
    public List<String> getAuthority(){
        return this.authority;
    }
}
