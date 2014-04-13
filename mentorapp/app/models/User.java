package models;

import java.util.ArrayList;

import play.db.ebean.Model.Finder;

public class User {
	public Long ean;
	public String name;
	public String password;
	public String currentEmployer;
	public String currentTitle;
	public String position;
	public String workExperience;
	public boolean isMentor;
	public boolean isMentee;
	
	/**
	 * Create a new user
	 * @param aName
	 * @param aPassword
	 */
	public User(String aName, String aPassword){
		name = aName;
		password = aPassword;
	}
	
	/**
	 * Validates log in.
	 * @param name
	 * @param password
	 * @return true if user name and password are correct
	 */
	public boolean validateUser(String name, String password){
		return false;
	}
	
	/**
	 * Add experience to user
	 * @param text work experience
	 * @param years number of years
	 */
	public void addExperience(String text, int num){
		workExperience = workExperience + text + num + ";";
	}
	
	/**
	 * Find Users in database
	 * @return list of users
	 */
	public static Finder<Long, User> find(){
		return new Finder<Long, User> (Long.class, User.class);
	}
}
