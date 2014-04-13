package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

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

	public Long ean;
	public String name;
	public String password;
	/**
	public String currentEmployer;
	public String currentTitle;
	public String position;
	public String workExperience;
	public boolean isMentor;
	public boolean isMentee;
	*/
	/**
	 * Create a new user
	 * 
	 * @param aName
	 *            user account name
	 * @param aPassword
	 *            user password
	 */
	public User(Long anEan, String aName, String aPassword) {
		ean = anEan;
		name = aName;
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

	/**
	 * Get users in database
	 * 
	 * @return list of users
	 */
	public static Finder<Long, User> find() {
		return new Finder<Long, User>(Long.class, User.class);
	}

	private static Set<User> users;
	static {
		users = new HashSet<User>();
		users.add(new User(1111111111111L, "Ryan Honrado", "password123"));
		users.add(new User(2222222222222L, "Michael Daniels", "password123"));
		users.add(new User(3333333333333L, "Charlemagne Doles", "password123"));
	}

	public static Set<User> findAll() {
		return new HashSet<User>(users);
	}

	public static User findByEan(Long ean) {
		for (User candidate : users) {
			if (candidate.ean.equals(ean)) {
				return candidate;
			}
		}
		return null;
	}

	public static Set<User> findByName(String term) {
		final Set<User> results = new HashSet<User>();
		for (User candidate : users) {
			if (candidate.name.toLowerCase().contains(term.toLowerCase())) {
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
