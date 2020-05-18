package com.feedback.dto;

public class UserDetailsDTO {
	public String name;
	public String priorityRole;
	public String roles;
	public String jwtToken;
	private String userName;

	public UserDetailsDTO(String userName, String name, String priorityRole, String roles, String jwtToken) {
		super();
		this.userName = userName;
		this.name = name;
		this.priorityRole = priorityRole;
		this.roles = roles;
		this.jwtToken = jwtToken;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the priorityRole
	 */
	public String getPriorityRole() {
		return priorityRole;
	}

	/**
	 * @param priorityRole the priorityRole to set
	 */
	public void setPriorityRole(String priorityRole) {
		this.priorityRole = priorityRole;
	}

	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}

	/**
	 * @return the jwtToken
	 */
	public String getJwtToken() {
		return jwtToken;
	}

	/**
	 * @param jwtToken the jwtToken to set
	 */
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
