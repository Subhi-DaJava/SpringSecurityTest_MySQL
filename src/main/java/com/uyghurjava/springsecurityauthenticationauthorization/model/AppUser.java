package com.uyghurjava.springsecurityauthenticationauthorization.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class AppUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String username;

    private String password;

    private int age;

    @Column(name = "email", unique = true, length = 50)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

  /*  @ManyToMany (fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();*/

}
