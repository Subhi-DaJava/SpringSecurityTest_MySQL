package com.uyghurjava.springsecurityauthenticationauthorization.service;

import com.uyghurjava.springsecurityauthenticationauthorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Vérifier, Remember-me, comment garder la session, deuxième essai changer le mot de passe
        System.out.println("LoadUseByUserName: " + username);

        //Vérifier si user existe ou non, stream pour looper
        Optional<Entry<String, Map<String, String>>> userOpt = userRepository.users
                .entrySet()
                .stream()
                .filter(un -> un.getKey().equals(username))
                .findFirst();

        if(!userOpt.isPresent())
            throw new UsernameNotFoundException("Not found this user"+ username);

        //Obtenir les informations d'user et faire comparer le mot de passe
        Map<String, String> userInfo = userOpt.get().getValue();
        String password = userInfo.get("password");
        String authority = userInfo.get("authority");

        //authority faut être un String
        //return new User(username, password, authority);
        return new User(username, password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(authority));
    }
}
