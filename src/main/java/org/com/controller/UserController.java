package org.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@RequestMapping(value={"/","/home"})
	public ModelAndView homePage(ModelAndView modelAndView){
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
}
