package com.mdkGame.DiceApp_JDBC.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mdkGame.DiceApp_JDBC.Domain.Games;
import com.mdkGame.DiceApp_JDBC.Services.GamesService;

@RestController
public class GamesController {

	@Autowired
	private GamesService gamesService;
	
	/////////////////////
	/*--POST REQUESTs--*/
	/////////////////////
	//POST -ADD NEW GAME TO A PLAYER (by playerId)
	@RequestMapping(method=RequestMethod.POST, value = "/players/{playerId}/games")
	public void newGameForPlayer(@PathVariable int playerId) {
		gamesService.addNewGameForPlayer(playerId);
	}
	
	////////////////////
	/*--GET REQUESTs--*/
	////////////////////
	///GET ALL GAMES for a Player by PlayerId
	@RequestMapping(method=RequestMethod.GET,value = "/players/{playerId}/games")
	public List<Games> getAllGames(@PathVariable int playerId) {
		return gamesService.getAllGamesForPlayer(playerId);
	}
	///GET ONE GAME for a Player by GameId and playerId (check if player owns that game before return)	
	@RequestMapping(method=RequestMethod.GET,value = "/players/{playerId}/games/{gameId}")
	public Games getGameByGameId(@PathVariable int gameId, @PathVariable int playerId) {
		Games reqGame = gamesService.getGameByGameId(gameId).get();
		if(reqGame.getPlayerId() == playerId) {
			return gamesService.getGameByGameId(gameId).get();
		}else {
			return null;
		}
		
	}

	///////////////////////
	/*--DELETE REQUESTs--*/
	///////////////////////
//	//DELETE ALL GAMES FOR PLAYER //Not needed in JDBC, Cascade property in table DDL does it automatically when deletieng a player
//	@RequestMapping(method=RequestMethod.DELETE, value = "/players/{playerId}/games")
//	public void deleteAllGamesForPlayer(@PathVariable int playerId) {
//		gamesService.deleteAllGamesForPlayer(playerId);
//				
//	}
//	
	////////////////////
	/*--EXTRA REQUESTs (Developing process)--*/
	////////////////////
	/*--CREATE GAME TABLE--*/
	@RequestMapping(method=RequestMethod.POST, value = "/games/createTables")
	public void createGameTable() {
		gamesService.createGameTable();
				
	}

	
}
