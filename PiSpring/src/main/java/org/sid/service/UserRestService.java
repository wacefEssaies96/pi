package org.sid.service;

import java.util.Collection;
import java.util.Optional;

import org.sid.entities.Users;
import org.sid.metier.iUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestService {
	@Autowired
	private iUserMetier userMetier;
	
	@RequestMapping(value="/save/user",method = RequestMethod.POST)
	public Users saveUser(@RequestBody Users u) {
		return userMetier.saveUser(u);
	}
	
	@RequestMapping(value="/users", method = RequestMethod.GET)
	public Collection<Users> getUsers(){
		return userMetier.consulterListUsers();
	}
	@RequestMapping(value="/user/{username}", method = RequestMethod.GET)
	public Optional<Users> getUser(@PathVariable String username){
		return userMetier.findUser(username);
	}
}
