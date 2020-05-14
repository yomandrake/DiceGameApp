package com.mdkGame.DiceApp_JDBC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiceGameViewController {
	
	@GetMapping("/dicesgame")
	public String dicesgame(@RequestParam(name = "user", required = false, defaultValue = "World") String name, Model model) {

		model.addAttribute("name", name);
		return "dicesgame"; 
	}

	
	@GetMapping("/dicesgame/{playerId}")
	public String dicesgameForPlayer(@PathVariable String playerId,@RequestParam(name = "user", required = false, defaultValue = "World") String name, Model model) {

		model.addAttribute("name", name);
		return "dicesgame"; 
	}
	
	
	
	
}
