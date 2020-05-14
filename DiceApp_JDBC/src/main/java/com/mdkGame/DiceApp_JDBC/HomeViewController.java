package com.mdkGame.DiceApp_JDBC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {
  
	@GetMapping("/")
	  public String home() {
	      return this.homeView();
	      
	  }
	
	@GetMapping("/home")
  public String homeView() {
      return "home";
  }
	
}
