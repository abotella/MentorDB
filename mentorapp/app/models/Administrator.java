package models;

public class Administrator
{
	String username, password;
	
	/**
	 * Creates a new Administrator
	 * @param userN the chosen username for the administrator
	 * @param pass the chosen password the administrator
	 */
	public Administrator(String userN, String pass)
	{
		username = userN;
		password = pass;
	}
	
	/**
	 * Sets a new username for the administrator
	 * @param newUserName the new username that Administrator will be changed to
	 */
	public void setUsername(String newUserName)
	{
		username = newUserName;
	}
	
	/**
	 * Sets a new password for the Administrator
	 * @param newPassword the new password that Administrator will have
	 */
	public void setPassword(String newPassword)
	{
		password = newPassword;
	}
	
	/**
	 * Verifies the Administrator's password for login
	 * @param pass the password the user entered that will be compared to Administrator's password
	 * @return whether or not the two passwords match
	 */
	public boolean verifyPassword(String pass)
	{
		return pass == password;
	}
	
}
