package org.sid.metier;

import java.util.Collection;
import java.util.Optional;

import org.sid.entities.Users;

public interface iUserMetier {
	public Users saveUser(Users u);
	public Collection<Users> consulterListUsers();
	public Optional<Users> findUser(String username);
}
