package com.uyghurjava.springsecurityauthenticationauthorization.config;

import com.uyghurjava.springsecurityauthenticationauthorization.handler.MyAccessDeniedHandler;
import com.uyghurjava.springsecurityauthenticationauthorization.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


// Se connecter avec WebSecurityConfiguration
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
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
                .antMatchers("/managerPage").hasAuthority("manager")
                // Autres rôles
                .antMatchers("/employeePage").hasAnyAuthority("manager","employee","user")
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
	  return new JdbcUserDetailsManager(dataSource); }


    // Créer l'algorithme cryptographique
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
