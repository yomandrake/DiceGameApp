package com.mdkGame.DiceApp_JDBC.Controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mdkGame.DiceApp_JDBC.Domain.Player;
import com.mdkGame.DiceApp_JDBC.Domain.PlayerDTO;
import com.mdkGame.DiceApp_JDBC.Services.GamesService;
import com.mdkGame.DiceApp_JDBC.Services.PlayerService;
import com.mdkGame.DiceApp_JDBC.Services.StatsService;

@RestController
public class PlayerController {

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private GamesService gamesService;
	
	@Autowired
	private StatsService statsService;
	
	////////////////////
	/*--POST REQUESTs--*/
	////////////////////
	//POST -ADD NEW PLAYER
	@RequestMapping(method=RequestMethod.POST, value = "/players")
	public ResponseEntity<String> addPlayer(@RequestBody Player newPlayer){		
		if(playerService.isNameUsed(newPlayer.getPlayerName().toLowerCase())) {
			return new ResponseEntity<>("Sorry the name is not Available. Try A different one",HttpStatus.CONFLICT);
		}else {
			playerService.addNewPlayer(newPlayer);
			return new ResponseEntity<>("Player Added Succesfully!!",HttpStatus.OK);
		}
		
	}
	////////////////////
	/*--GET REQUESTs--*/
	////////////////////
	///GET ALL PLAYERS
	@RequestMapping("/players")//GET ALL PLAYERS
	public List<PlayerDTO> getAllPlayers() {
		//Get All players in a List, and calculate their Stats
		List<Player> allPlayers = playerService.getAllPlayers();
		allPlayers.forEach(player -> player.setAvgIsWin(statsService.getStatics(gamesService.getAllGamesForPlayer(player.getPlayerId())).getAvgIsWin()));
		//Create List of DTO, and populate with objectos converted to DTOs
		List<PlayerDTO> allPlayersDTO = new ArrayList<>();
		allPlayers.forEach(player -> allPlayersDTO.add(new PlayerDTO(player)));
		return allPlayersDTO;
	}
	///GET PLAYER BY ID
	@RequestMapping(method=RequestMethod.GET, value = "/players/{playerId}")
	public PlayerDTO getPlayer(@PathVariable int playerId) {
		//Get a Player by PlayerId
		Player requestedPlayer = playerService.getPlayerById(playerId); 
		//Calculate Stats
		requestedPlayer.setAvgIsWin(statsService.getStatics(gamesService.getAllGamesForPlayer(playerId)).getAvgIsWin());
		return new PlayerDTO(requestedPlayer);
	}
	
	////////////////////	
	/*--PUT REQUESTs--*/
	////////////////////
	//PUT -UPDATE PLAYER
	@RequestMapping(method=RequestMethod.PUT, value = "/players")
	public void updatePlayer(@RequestBody Player newPlayer) {		
		playerService.updatePlayer(newPlayer);
	}
	
	///////////////////////
	/*--DELETE REQUESTs--*/
	///////////////////////
	//DELETE PLAYER
	@RequestMapping(method=RequestMethod.DELETE, value = "/players/{playerId}")
	public void deletePlayer(@PathVariable int playerId) {
		//Next is not needed in JDBC if the table are well initiated with Cascade Constrain
		//gamesService.deleteAllGamesForPlayer(playerId);
		playerService.deletePlayer(playerId);
	}
	
		
	////////////////////
	/*--EXTRA REQUESTs (Developing process)--*/
	////////////////////
	//GET Availability confirmation for a particular Name
	@RequestMapping(method=RequestMethod.GET, value = "/players/isNameUsed/{playerName}")
	public ResponseEntity<String> checkNameIsAvailable(@PathVariable String playerName){
				
		if(playerService.isNameUsed(playerName.toLowerCase())) {
			return new ResponseEntity<>("Sorry the name is not Available. Try A different one",HttpStatus.CONFLICT);
		}else {
			return new ResponseEntity<>("Name available!!",HttpStatus.OK);
		}
		
	}
	//POST METHOD for creatTables
	@RequestMapping(method=RequestMethod.POST, value = "/players/createTables")
	public void createPlayerTable() {
		playerService.createTablePlayer();
	}
	
}
