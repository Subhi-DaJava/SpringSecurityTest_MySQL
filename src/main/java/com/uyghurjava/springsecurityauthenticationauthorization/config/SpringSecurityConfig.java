package com.uyghurjava.springsecurityauthenticationauthorization.config;

import com.uyghurjava.springsecurityauthenticationauthorization.handler.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


// Se connecter avec WebSecurityConfiguration
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
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
                // Sans auth
                .antMatchers("/loginPage").permitAll()
                // Autorisation /droit pour un Path
                .antMatchers("/adminPage").hasAuthority("admin")
                // Rôle
                .antMatchers("/managerPage").hasRole("manager")
                // Autres rôles
                .antMatchers("/employeePage").hasAnyRole("manager","employee")
                //les autres -> s'authentifier
                .anyRequest().authenticated();

        //http.csrf().disable();

        //Log Out : form -> action
        http.logout()
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/loginPage")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")); //Non recommandé

        // Gestion des exceptions
       /* http.exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler);*/

        http.exceptionHandling()
                .accessDeniedPage("/SansAutorisation");

        // Remember-me
        http.rememberMe()
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(60); //Plus long que session timeout


    }

    // Créer l'algorithme cryptographique
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
