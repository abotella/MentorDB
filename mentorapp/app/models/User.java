package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

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
	public boolean isMentor;
	public boolean isMentee;
	
	/**
	 * Create a new user
	 * 
	 * @param aName
	 *            user account name
	 * @param aPassword
	 *            user password
	 */
	public User(String aName, String aPassword) {
		userName = aName;
		password = aPassword;
	}

	/**
	 * Validates log in.
	 * 
	 * @param name
	 *            name of user
	 * @param password
	 *            user's password
	 * @return true if user name and password are correct
	 */
	public boolean validateUser(String name, String password) {
		return false;
	}

	public static Set<User> users = new HashSet<User>();

	public static Set<User> findAll() {
		return new HashSet<User>(users);
	}

	public static Set<User> findByName(String term) {
		final Set<User> results = new HashSet<User>();
		for (User candidate : users) {
			if (candidate.userName.toLowerCase().contains(term.toLowerCase())) {
				results.add(candidate);
			}
		}
		return results;
	}

	public static boolean remove(User user) {
		return users.remove(user);
	}

	public static void add(User user) {
		users.add(user);
	}
}
