package com.uyghurjava.springsecurityauthenticationauthorization.repository;

import com.uyghurjava.springsecurityauthenticationauthorization.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findUserByEmail(String userEmail);
    AppUser findUserByUsername(String userName);

}
