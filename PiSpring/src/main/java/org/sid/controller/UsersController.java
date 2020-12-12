package org.sid.controller;
import org.sid.entities.Users;
import org.sid.metier.iUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {
	@Autowired
	private iUserMetier service;
	
		@RequestMapping("/inscri")
	    public String inscri(Model model) {
			Users user = new Users();
			model.addAttribute("u",user);
	        return "inscri.html";
	    }
		@RequestMapping("/add")
	    public String addUser(Users u) {
			service.saveUser(u);
	        return "inscri.html";
	    }
}
