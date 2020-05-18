package com.feedback.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feedback.dto.UserDTO;
import com.feedback.dto.UserDetailsDTO;
import com.feedback.entities.Role;
import com.feedback.entities.UserRole;
import com.feedback.entities.Users;
import com.feedback.jpa.repositories.UsersRepository;
import com.feedback.service.UserService;

@Service
public class JwtUserDetailsService implements UserService, UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	@Transactional
	public Map<String, Object> loadActiveUser(String username) throws UsernameNotFoundException {
		Users user = usersRepository.findByUserNameAndStatus(username, "Active");
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		Set<UserRole> userRoles = user.getUserRoles();
		StringBuilder roles = new StringBuilder();
		List<GrantedAuthority> authorities = new ArrayList<>();
		userRoles.forEach(userRole -> {
			Role role = userRole.getRole();
			authorities.add(new SimpleGrantedAuthority(role.getRoleDesc()));
			roles.append(role.getRoleDesc());
			roles.append(", ");		
        });
		String rolesStr = roles.toString().replaceAll(", $", "");
		UserDetailsDTO userDetailsDTO = new UserDetailsDTO(user.getUserName(), user.getFullName(),rolesStr,  rolesStr, null);
      
		User userDetails = new User(user.getUserName(), user.getPassword(),
				authorities);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userDetails", userDetailsDTO);
		map.put("user", userDetails);
        
        return map;
	}
	
	public Users save(UserDTO user) {
		Users newUser = new Users();
		newUser.setEmail(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		System.out.println("Password " + bcryptEncoder.encode(user.getPassword()));
		return usersRepository.save(newUser);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepository.findByUserNameAndStatus(username, "Active");
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		Set<UserRole> userRoles = user.getUserRoles();
		List<GrantedAuthority> authorities = new ArrayList<>();
		userRoles.forEach(userRole -> {
			Role role = userRole.getRole();
			authorities.add(new SimpleGrantedAuthority(role.getRoleDesc()));
        });
      
		User userDetails = new User(user.getUserName(), user.getPassword(),
				authorities);
        
        return userDetails;
	}
}
