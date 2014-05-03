package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.avaje.ebean.Ebean;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

/**
 * The purpose of this class is to create users for WebApp.
 *
 */
@Entity
public class User extends Model {

	@Id
	public Long id;
	
	@Constraints.Required
	public String userName;
	public String password;
	
	public String displayName;
	public String currentEmployer;
	public String currentTitle;
	public String workExperience;
	public String mentors;
	public String mentees;
	public boolean isMentor;
	public boolean isMentee;
	
	
	/**
	 * Create a new user
	 * 
	 * @param aName user account name
	 * @param aPassword user password
	 */
	public User(String aName, String aPassword) {
		userName = aName;
		password = aPassword;
	}

	/**
	 * Removes user from database
	 * @param user
	 * @return true if removed, false if not removed
	 */
	public static boolean remove(User user) {
		return users.remove(user);
	}

	/**
	 * Adds user to the database
	 * @param user a user
	 */
	public static void add(User user) {
		users.add(user);
	}
	
	/**
	 * Checks if the user has the given criteria
	 * @param criteria a criteria
	 * @return true if criteria is found, false if not
	 */
	public boolean contains (String criteria){
		if (criteria.equals("")){
			return false;
		}
		else if (criteria.toLowerCase().equals(currentTitle) || criteria.toLowerCase().equals(currentEmployer)){
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a mentor to the mentors string list
	 * @param aMentor the mentor
	 */
	public void addMentor (String aMentor){
		mentors = mentors + aMentor + ";";
	}
	
	/**
	 * Adds a mentee to the mentees string list
	 * @param aMentee the mentee
	 */
	public void addMentee (String aMentee){
		mentees = mentees + aMentee + ";";
	}
	
	public boolean removeMentor (String aMentor){
		return false;
	}
	public static Set<User> users = new HashSet<User>();
}
