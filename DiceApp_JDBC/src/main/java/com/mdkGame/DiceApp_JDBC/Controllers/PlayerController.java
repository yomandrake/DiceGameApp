package com.mdkGame.DiceApp_JDBC.Controllers;

import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PlayerController {

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private GamesService gamesService;
	
	@Autowired
	private StatsService statsService;
	
	/////////////////////
	/*--POST REQUESTs--*/
	/////////////////////
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
	public ResponseEntity<?> getAllPlayers() {
		List<Player> listPlayers;
		try {
			listPlayers = playerService.getAllPlayers();
			listPlayers.forEach(player -> player.setAvgIsWin(statsService.getStatics(gamesService.getAllGamesForPlayer(player.getPlayerId())).getAvgIsWin()));
			//Create List of DTO, and populate with objetos converted to DTOs
			List<PlayerDTO> allPlayersDTO = new ArrayList<>();
			listPlayers.forEach(player -> allPlayersDTO.add(new PlayerDTO(player)));			
			return new ResponseEntity<>(allPlayersDTO,HttpStatus.OK);
		}
		 catch (Exception e)
        {
            String errorString =  "ERROR " + e.getMessage();
            System.out.println(errorString);
            return new ResponseEntity<>( errorString , HttpStatus.BAD_REQUEST);
        }
	}
	
	///GET PLAYER BY ID
	@RequestMapping(method=RequestMethod.GET, value = "/players/{playerId}")
	public ResponseEntity<?> getPlayer(@PathVariable int playerId) {
		Player requestedPlayer;
		try {
			requestedPlayer = playerService.getPlayerById(playerId); 
			return new ResponseEntity<>(new PlayerDTO(requestedPlayer),HttpStatus.OK);
		}
		 catch (Exception e)
        {
            String errorString =  "ERROR " + e.getMessage();
            System.out.println(errorString);
            return new ResponseEntity<>( errorString , HttpStatus.BAD_REQUEST);
        }
		
	}
	
	
	
	////////////////////	
	/*--PUT REQUESTs--*/
	////////////////////
	//PUT -UPDATE PLAYER
	@RequestMapping(method=RequestMethod.PUT, value = "/players")
	public ResponseEntity<String> updatePlayer(@RequestBody Player newPlayer) {
		
		try {
			playerService.updatePlayer(newPlayer);
			return new ResponseEntity<>("Player Updated",HttpStatus.OK);
		}
		 catch (Exception e)
        {
            String errorString =  "ERROR " + e.getMessage();
            System.out.println(errorString);
            return new ResponseEntity<>( errorString , HttpStatus.BAD_REQUEST);
        }
		
		
		
	}
	
	///////////////////////
	/*--DELETE REQUESTs--*/
	///////////////////////
	//DELETE PLAYER
	@RequestMapping(method=RequestMethod.DELETE, value = "/players/{playerId}")
	public ResponseEntity<String> deletePlayer(@PathVariable int playerId) {
		try {
			//Next is not needed in JDBC if the table are well initiated with Cascade Constrain
			//gamesService.deleteAllGamesForPlayer(playerId);
			playerService.deletePlayer(playerId);
			return new ResponseEntity<>( "Player and all its games deleted" , HttpStatus.OK);
		}
		 catch (Exception e)
        {
            String errorString =  "ERROR " + e.getMessage();
            System.out.println(errorString);
            return new ResponseEntity<>( errorString , HttpStatus.BAD_REQUEST);
        }
		
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

	
}
