package com.townhall.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.townhall.api.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	// as a rule for custom method, it should start with findBy or getBy
	//User findByUsername(String username);  // Query DSL
	
	// JPQL or HQL
	//@Query("from User where username= :param_uname")
	//List<User> find(@Param("param_uname") String username);
	
	Optional<User> findByEmail(String email);

	Optional<User> findByUsernameOrEmail(String username, String email);

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
