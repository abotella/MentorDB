package models;

public class Administrator
{
	String username, password;
	
	public Administrator(String userN, String pass)
	{
		username = userN;
		password = pass;
	}
	
	public void setUsername(String newUserName)
	{
		username = newUserName;
	}
	
	public void setPassword(String newPassword)
	{
		password = newPassword;
	}
	
	public boolean verifyPassword(String pass)
	{
		return pass == boolean;
	}
	
}
