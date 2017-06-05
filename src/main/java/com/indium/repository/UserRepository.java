package com.indium.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.indium.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select u from User u where u.username = ?1")
	Optional<User> findOneByUsername(String username);
	
	@Query("select u from User u where u.username = ?1 and u.passwordHash = ?2")
	Optional<User> findUserByIDAndPassword(String username, String passwordHash);
}
