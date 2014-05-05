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
import controllers.*;

/**
 * The purpose of this class is to create users for WebApp.
 *
 */
@Entity
public class User extends Model {

	@Id
	public Long id;
	
	@Constraints.Required (message = "Username is required!")
	public String userName;
	
	@Constraints.Required (message = "Password is required!")
	public String password;
	
	@Constraints.Required (message = "Display Name is required!")
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
	 * Adds user to the database
	 * @param user a user
	 */
	public static void add(User user) {
		users.add(user);
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
	
	public static void create(User user) {
		user.password = Session.encryptPassword(user.password);
		user.save();
	}
	
	public static void delete(Long id) {
		UserApplication.find().ref(id).delete();
	}

	public void update(User user) {
		user.password = Session.encryptPassword(user.password);
		user.update(this.id);
	}
	
	public static User authenticate(String username, String password) {
		User user = UserApplication.find().where().eq("userName", username).findUnique();
		if (Session.checkPassword(password, user.password))
			return user;
		else
			return null;
	}
	public static Set<User> users = new HashSet<User>();
}
