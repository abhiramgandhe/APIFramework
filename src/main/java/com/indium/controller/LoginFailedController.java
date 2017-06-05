package com.indium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginFailedController {

	@RequestMapping("/unauthenticated")
	public String unauthenticated() {
	    return "redirect:/?error=true";
	}
	
}
