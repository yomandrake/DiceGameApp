package com.mdkGame.DiceApp_JDBC.Controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mdkGame.DiceApp_JDBC.Domain.Games;
import com.mdkGame.DiceApp_JDBC.Domain.PlayerDTO;
import com.mdkGame.DiceApp_JDBC.Services.GamesService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GamesController {

	@Autowired
	private GamesService gamesService;
	
	/////////////////////
	/*--POST REQUESTs--*/
	/////////////////////
	//POST -ADD NEW GAME TO A PLAYER (by playerId)
	@RequestMapping(method=RequestMethod.POST, value = "/players/{playerId}/games")
	public ResponseEntity<String> newGameForPlayer(@PathVariable int playerId) {

		try {
			gamesService.addNewGameForPlayer(playerId);
			return new ResponseEntity<>("New Game Registered",HttpStatus.OK);
		}
		 catch (Exception e)
        {
            String errorString =  "ERROR " + e.getMessage();
            System.out.println(errorString);
            return new ResponseEntity<>( "Error registering new game" , HttpStatus.BAD_REQUEST);
        }
		
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
	public ResponseEntity<?> getGameByGameId(@PathVariable int gameId, @PathVariable int playerId) {

		try {
			Games reqGame = gamesService.getGameByGameId(gameId).get();
			if(reqGame.getPlayerId() == playerId) {
				return new ResponseEntity<>(reqGame , HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Something is wrong check data",HttpStatus.BAD_REQUEST);
			}
			
		}
		 catch (Exception e)
        {
            String errorString =  "ERROR " + e.getMessage();
            System.out.println(errorString);
            return new ResponseEntity<>( "Something is wrong check data" , HttpStatus.BAD_REQUEST);
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

}
