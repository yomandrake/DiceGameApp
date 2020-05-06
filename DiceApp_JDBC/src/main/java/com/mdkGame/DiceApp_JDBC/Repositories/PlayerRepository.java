package com.mdkGame.DiceApp_JDBC.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.mdkGame.DiceApp_JDBC.Domain.Player;


public interface PlayerRepository extends CrudRepository<Player, Integer>{
	  
//	@Query("select firstName, lastName from User u where u.emailAddress = :email")
//	  User findByEmailAddress(@Param("email") String email);
}
