package com.mdkGame.DiceApp_JDBC.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mdkGame.DiceApp_JDBC.Domain.Player;
import com.mdkGame.DiceApp_JDBC.Repositories.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//GET PLAYER BY playerId//
	public Player getPlayerById(int playerId) {
		return playerRepository.findById(playerId).get();		 
	}
	
	///GET ALL PLAYERS///
	public List<Player> getAllPlayers() {
		List<Player> allPlayers = new ArrayList<Player>();
		//playerRepository.findAll().forEach(allPlayers::add);//With method reference
		playerRepository.findAll().forEach(p -> allPlayers.add(p));//With lambda
		return allPlayers;
	}
	
	//POST -ADD NEW PLAYER//
	public void addNewPlayer(Player newPlayer) {
		if(newPlayer.getPlayerName()=="") {
			newPlayer.setPlayerName("Anonimo");
		}
		newPlayer.setPlayerRegDate(LocalDateTime.now().toString());		
		playerRepository.save(newPlayer);
	}
	
	//PUT or UPDATE PLAYER//
	public void updatePlayer(Player updatePlayer) {
		if(updatePlayer.getPlayerName()=="") {
			updatePlayer.setPlayerName("Anonimo");
		}
		//Set regDate to updatePlayer based on the original registered on to maintain the original 
		updatePlayer.setPlayerRegDate(playerRepository.findById(updatePlayer.getPlayerId()).get().getPlayerRegDate());
		playerRepository.save(updatePlayer);
	}
	
	//DELETE PLAYER//
	public void deletePlayer(int playerId) {
		playerRepository.deleteById(playerId);
	}
	
	//GET Name availability// 
	public boolean isNameUsed(String playerName) {
		List<String> usedNames = new ArrayList<String>();
		List<Player> registeredPlayers = this.getAllPlayers();
		for (Player player : registeredPlayers) {
			usedNames.add(player.getPlayerName().toLowerCase());
		}
		return usedNames.contains(playerName);
	}
	

}
