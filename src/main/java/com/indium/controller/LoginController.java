package com.indium.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.indium.domain.User;
import com.indium.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView displayLoginPage() {
		ModelAndView model = new ModelAndView("index");
		User user = new User();
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user) {

		ModelAndView model = null;

		try {
			boolean isValidUser = userService.isValidUser(user.getUsername(), user.getPasswordHash());

			if (isValidUser) {
				System.out.println("User Login Successful");
				request.setAttribute("loggedInUser", user.getUsername());
				model = new ModelAndView("welcome");
			} else {
				model = new ModelAndView("redirect:/?error=true");
				model.addObject("user", user);
				request.setAttribute("error", "true");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
}