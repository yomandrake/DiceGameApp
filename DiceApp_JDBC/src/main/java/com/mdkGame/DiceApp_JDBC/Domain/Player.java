package com.mdkGame.DiceApp_JDBC.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;

public class Player {
	@Id
	private int playerId;
	private String playerLogName;
	private String playerLogPass;
	private String playerName;
	private String playerRegDate;
	@Transient
	private float playerWinStats = (float) 0.00;

	@PersistenceConstructor
	public Player(int playerId, String playerLogName, String playerLogPass, String playerName, String playerRegDate) {
		this.playerId = playerId;
		this.playerLogName = playerLogName;
		this.playerLogPass = playerLogPass;
		this.playerName = playerName;
		this.playerRegDate = playerRegDate;
	}
	
	public Player() {
		
	}


	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerLogName() {
		return playerLogName;
	}

	public void setPlayerLogName(String playerLogName) {
		this.playerLogName = playerLogName;
	}

	public String getPlayerLogPass() {
		return playerLogPass;
	}

	public void setPlayerLogPass(String playerLogPass) {
		this.playerLogPass = playerLogPass;
	}

	public String getPlayerRegDate() {
		return playerRegDate;
	}

	public void setPlayerRegDate(String playerRegDate) {
		this.playerRegDate = playerRegDate;
	}

	public float getAvgIsWin() {
		return playerWinStats;
	}

	public void setAvgIsWin(float avgIsWin) {
		this.playerWinStats = avgIsWin;
	}
	
	
	

}
