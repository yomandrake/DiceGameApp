package com.mdkGame.DiceApp_JDBC.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mdkGame.DiceApp_JDBC.Domain.Games;
import com.mdkGame.DiceApp_JDBC.Repositories.GamesRepository;

@Service
public class GamesService {
	
	@Autowired
	private GamesRepository gamesRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addNewGameForPlayer(int playerId) {
		Games newGame = new Games(playerId);
		gamesRepository.save(newGame);
		
	}
	
	public Optional<Games> getGameByGameId(int gameId) {
		return gamesRepository.findById(gameId);
	}
	
	public List<Games> getAllGames() {
		List<Games> allGames = new ArrayList<>();
		gamesRepository.findAll().forEach(allGames::add);
		return allGames;
	}
	
	public List<Games> getAllGamesForPlayer(int playerId) {
		List<Games> allGamesForPlayer = new ArrayList<>();
		
		//JPA method in crud repository
		//gamesRepository.findByPlayerPlayerId(playerId).forEach(allGamesForPlayer::add);
		//JPA return
		//return allGamesForPlayer;
				
		//JDBC query method
//		String queryAllGamesForPlayer = "SELECT * FROM games";
//		List<Map<String, Object>> gamesForPlayer = jdbcTemplate.queryForList(queryAllGamesForPlayer, new Object() = playerId, new GamesRowMapper());
		//JDBC return
		//Check queryForList()
		//return jdbcTemplate.query(queryAllGamesForPlayer, new GamesRowMapper());
		
		allGamesForPlayer = gamesRepository.findAllGamesForPlayer(String.valueOf(playerId));
		return allGamesForPlayer;
	}

//	public void deleteAllGamesForPlayer(int playerId) {
////		List<Games> allGamesForPlayer = this.getAllGamesForPlayer(playerId);
////		for (Games games : allGamesForPlayer) {
////			gamesRepository.deleteById(games.getGameId());
////		}		
//	}


}
