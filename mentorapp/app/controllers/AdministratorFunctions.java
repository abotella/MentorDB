package controllers;
import play.mvc.Controller;
import models.*;
public class AdministratorFunctions extends Controller
{
	/**
	 * Validates the user's login credentials
	 * @param userN the username that the user entered
	 * @param pass the password that the user entered
	 * @return whether or not the user entered valid information
	 */
	public boolean validate(String userN, String pass)
	{
		return false;
	}
	
	/**
	 * Responds to a message that was sent to the administrator by a user
	 * @param message the message that will be sent to the user
	 */
	public void respond(Message message)
	{
		
	}
	
	/**
	 * Searches for a user profile based on a username
	 * @param username the username that will be used to search for a User profile
	 * @return the User that is attached to the username
	 */
	public User searchUser(String username)
	{
		return null;
	}
	
	/**
	 * Access a User's profile to verify information about the user
	 * @param user the User that the Administrator wishes to access
	 */
	public void accessUserProfile(User user)
	{
		
	}
	
	/**
	 * Resets a User's password to a default password based on username
	 * @param username the User to reset the password for
	 * @return whether or not the password was successfully changed
	 */
	public boolean resetUserPassword(String username)
	{
		return false;
	}
	
	/**
	 * Resets a User's password to a password given to the Administrator by the User
	 * @param username the User to reset the password for
	 * @param newPassword the password given to the Administrator by the User that will be the
	 * User's new passowrd
	 * @return whether or not the password was successfully changed
	 */
	public boolean resetUserPassword(String username, String newPassword)
	{
		return false;
	}
}
