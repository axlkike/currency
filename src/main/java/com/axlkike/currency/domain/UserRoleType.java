package com.axlkike.currency.domain;

/*
 * types of roles of the web application
 */
public enum UserRoleType {
	USER("USER"),
	ADMIN("ADMIN");

	String userProfileType;

	private UserRoleType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
