package com.feedback.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

	Map<String, Object> loadActiveUser(String userName) throws UsernameNotFoundException;
}
