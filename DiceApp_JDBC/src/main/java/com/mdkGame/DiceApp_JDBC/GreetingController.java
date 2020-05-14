package com.mdkGame.DiceApp_JDBC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

		model.addAttribute("name", name);
		return "greeting";
	}
	
	@GetMapping("/user/greeting")
	public String userGreeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

		model.addAttribute("name", name);
		return "greeting";
	}
 
	
}
