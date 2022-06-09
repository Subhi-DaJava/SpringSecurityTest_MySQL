package com.uyghurjava.springsecurityauthenticationauthorization.config;

import com.uyghurjava.springsecurityauthenticationauthorization.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


// Se connecter avec WebSecurityConfiguration
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //La page de Login
        http.formLogin()
                //loginPage.html: form - action
                .loginProcessingUrl("/login")
                //ouvrir la page de loginPage
                .loginPage("/loginPage")
                .successForwardUrl("/")
                .failureForwardUrl("/fail");

        //Autorisation
        http.authorizeHttpRequests()
                //sans auth
                .antMatchers("/loginPage").permitAll()
                //les autres -> s'authentifier
                .anyRequest().authenticated();
    }

    // Créer l'algorithme cryptographique
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
