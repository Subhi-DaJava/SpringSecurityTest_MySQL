package com.uyghurjava.springsecurityauthenticationauthorization.service;

import com.uyghurjava.springsecurityauthenticationauthorization.model.AppUser;
import com.uyghurjava.springsecurityauthenticationauthorization.repository.AuthorityRepository;
import com.uyghurjava.springsecurityauthenticationauthorization.repository.RoleRepository;
import com.uyghurjava.springsecurityauthenticationauthorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Vérifier, Remember-me, comment garder la session, deuxième essai changer le mot de passe
        System.out.println("LoadUseByUserName: " + username);

        //Vérifier si user existe ou non, stream pour looper
        AppUser appUser = userRepository.findUserByUsername(username);

        if(appUser == null)
            throw new UsernameNotFoundException("Not found this user" + username);

        //Obtenir les informations d'user et faire comparer le mot de passe

        String password = appUser.getPassword();
        //String authority = String.valueOf(user.getAuthorities());

        Collection<GrantedAuthority> authorities =
                appUser.getRoles()
                        .stream()
                        .map(appRole -> new SimpleGrantedAuthority(appRole.getRoleName()))
                        .collect(Collectors.toList());

        return new User(username, password, authorities);

    }
}
