package com.feedback.jpa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.feedback.entities.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

	Users findByEmail(String email);
	Users findByUserNameAndStatus(String userName, String status);
}

