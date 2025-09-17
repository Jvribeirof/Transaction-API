package com.unibanco.itau.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "api_key")
public class ApiKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String key;

    @OneToOne
    @JoinColumn(name = "users_id")
    private Users users;

    public ApiKey(String key) {
        this.key = key;
    }

    public ApiKey(){}

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public Users getUsers() {
        return users;
    }
    public void setUsers(Users users) {
        this.users = users;
    }
}
