package com.uyghurjava.springsecurityauthenticationauthorization.repository;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class UserRepository {

    //UserA, UserB
    public Map<String, Map<String,String>> users;
    {
        // admin, 1234
        Map<String, String> infoUserA = new LinkedHashMap<>();
        infoUserA.put("password", "$2a$10$l63YJdJLkedZc1S4ay/U/OQ/L7IUvjutDF/7cHlkhSeN00K2vvUay");
        infoUserA.put("authority","admin,user,ROLE_manager");

        // user1, 0000
        Map<String, String> infoUserB = new LinkedHashMap<>();
        infoUserB.put("password", "$2a$12$N7/2jkt8HdaGwFNQMEGDIurrsuM5TA48A5gX.KVKG/Ryt7bPR5TX2");
        infoUserB.put("authority","user,ROLE_employee");

        // user2, 2222, (1111)maintenant changé pour tester Remember-me, remplacé pendant l'exécution, il arrive à reconnecter automatiqument
        Map<String, String> infoUserC = new LinkedHashMap<>();
        infoUserC.put("password", "$2a$12$jualOiSA8nlJqRNhFAsBJeN6BJCS0ZZvLY3drhOneEc1ffs9Leniy");
        infoUserC.put("authority","user,ROLE_employee");




        users = new LinkedHashMap<>();

        users.put("admin", infoUserA);
        users.put("user1", infoUserB);
        users.put("user2", infoUserC);

        System.out.println(users);
    }

}
