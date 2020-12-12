package org.sid.metier;

import java.util.Collection;
import java.util.Optional;

import org.sid.dao.IUserRepository;
import org.sid.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IUserMetierImpl implements iUserMetier {

	@Autowired
	IUserRepository userRepository;
	
	@Override
	public Users saveUser(Users u) {
		// TODO Auto-generated method stub
		return userRepository.save(u);
	}

	@Override
	public Collection<Users> consulterListUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Optional<Users> findUser(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	

}
