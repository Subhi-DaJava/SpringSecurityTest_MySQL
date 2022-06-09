package com.uyghurjava.springsecurityauthenticationauthorization.repository;

import com.uyghurjava.springsecurityauthenticationauthorization.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
