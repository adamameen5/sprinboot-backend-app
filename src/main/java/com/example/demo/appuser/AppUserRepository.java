package com.example.demo.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query("SELECT a FROM AppUser a WHERE a.email=?1")
    Optional<AppUser> findAppUserByEmail(String email);

    @Query("SELECT a FROM AppUser a WHERE a.name=?1")
    Optional<AppUser> findAppUserByName(String name);
}
