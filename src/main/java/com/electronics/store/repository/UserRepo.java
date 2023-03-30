package com.electronics.store.repository;

import com.electronics.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    User findByEmailAndPassword(String email, String passwrd);

    List<User> findByNameContaining(String keywords);

}
