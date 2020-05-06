package com.mdkGame.DiceApp_JDBC.Domain;

import java.time.LocalDateTime;
import java.util.Random;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

public class Games {
	
	@Id
	private int gameId;
	private String gameDateTime;
	private int dice1;
	private int dice2;
	private int isWin;
	
//	@MappedCollection(idColumn = "playerId")
//	Set<Player> playerId;
	
//	private Player player;//Foreign Key
	private int playerId;
	
	public Games() {
					
	}
	@PersistenceConstructor
	public Games(int playerId) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		this.setGameDateTime(currentDateTime.toString());
		this.dice1 = new Random().nextInt(7);
		this.dice2 = new Random().nextInt(7);
		///Decide whether is a Win or Not
		if(this.dice1 == 6 && this.dice2 == 6) {
			//this.setWin(true);
			this.isWin = 1;
		}else {
			//this.setWin(false);
			this.isWin = 0;
		}
		
		//Set playerId to persist in DB, for non relational porpouse
		this.setPlayerId(playerId);
		
		//Set Id Foreign Key
//		Player player = new Player();
//		player.setPlayerId(playerId);
//		this.player = player;		
	}
	
	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getIsWin() {
		return isWin;
	}

	public void setIsWin(int isWin) {
		this.isWin = isWin;
	}

//	public Player getPlayer() {
//		return player;
//	}
//
//	public void setPlayer(Player player) {
//		this.player = player;
//	}

	public int getDice1() {
		return dice1;
	}


	public void setDice1(int dice1) {
		this.dice1 = dice1;
	}


	public int getDice2() {
		return dice2;
	}


	public void setDice2(int dice2) {
		this.dice2 = dice2;
	}

	public String getGameDateTime() {
		return gameDateTime;
	}

	public void setGameDateTime(String gameDateTime) {
		this.gameDateTime = gameDateTime;
	}


	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	

	

}
