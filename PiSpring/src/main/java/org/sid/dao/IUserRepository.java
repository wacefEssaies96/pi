package org.sid.dao;

import java.util.Optional;

import org.sid.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<Users,Long> {

	Optional<Users> findByUsername(String username);
}
