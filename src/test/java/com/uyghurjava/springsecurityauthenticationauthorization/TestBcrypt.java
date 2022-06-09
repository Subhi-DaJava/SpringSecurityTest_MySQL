package com.uyghurjava.springsecurityauthenticationauthorization;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestBcrypt {

   /* public static void main(String[] args) {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                String bcrypt = passwordEncoder.encode("1234");
                System.out.println(bcrypt);
                boolean matches = passwordEncoder.matches("1234", bcrypt);

                System.out.println(matches);

                String bcrypt_new = passwordEncoder.encode("5678");
                System.out.println(bcrypt);
                boolean matches_new = passwordEncoder.matches("5678", bcrypt_new);
                System.out.println(matches_new);

                //$2a$10$l63YJdJLkedZc1S4ay/U/OQ/L7IUvjutDF/7cHlkhSeN00K2vvUay

                //$2a$10$l63YJdJLkedZc1S4ay/U/OQ/L7IUvjutDF/7cHlkhSeN00K2vvUay

    }*/
}
