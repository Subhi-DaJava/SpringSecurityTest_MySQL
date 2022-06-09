package com.uyghurjava.springsecurityauthenticationauthorization.repository;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class UserRepository {

    //UserA, UserB
    public Map<String, Map<String,String>> users;
    {
        // UserA, 1234
        Map<String, String> infoUserA = new LinkedHashMap<>();
        infoUserA.put("password", "$2a$10$l63YJdJLkedZc1S4ay/U/OQ/L7IUvjutDF/7cHlkhSeN00K2vvUay");
        infoUserA.put("authority","admin, normal, ROLE_manager");

        //UserB, 5678
        Map<String, String> infoUserB = new LinkedHashMap<>();
        infoUserB.put("password", "$2a$10$J5uOwmVuOwPy0To6gy4QWOUz3pKW5sjjW/MUnetaMGQLXsuZumNwy");
        infoUserB.put("authority", "normal, ROLE_employee");

        users = new LinkedHashMap<>();

        users.put("UserA", infoUserA);
        users.put("UserB", infoUserB);

        System.out.println(users);
    }

}
