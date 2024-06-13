package com.SL.DMSApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	 

		@GetMapping("/home")
		public String home()
		{
			return "home";
		}
		
		
		 @GetMapping("/layout")
		    public String home(Model model) {
		        model.addAttribute("contentFragment", "home");
		        return "fragments/layout";
		    }
		
		
}
