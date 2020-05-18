package com.feedback.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feedback.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByRoleDesc(String roleName);

}
