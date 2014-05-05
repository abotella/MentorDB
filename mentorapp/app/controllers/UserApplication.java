package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.avaje.ebean.Ebean;

import play.mvc.*;
import play.data.*;
import play.db.ebean.Model.Finder;
import scala.collection.parallel.ParIterableLike.Find;
import static play.data.Form.*;
import views.html.*;
import models.*;


public class UserApplication extends Controller {

	public static Form<User> userForm = Form.form(User.class);
	public static Form<User> loginForm = Form.form(User.class);
	public static String currentText;
	
	/**
	 * Main page
	 */
	public static Result index(String someUser) {
		User tmp = new User(null,null);
		User tmp2 = new User(null,null);
		if (!someUser.equals("")){
			
			for (User a : find().findList()) {
				if (a.userName.toLowerCase().equals(currentText.toLowerCase())){
					tmp = a;
					break;
				}
			}
			
			
			for (User b : find().findList()) {
				if (b.userName.toLowerCase().equals(someUser.toLowerCase())){
					tmp2 = b;
					break;
				}
			}
			tmp.addMentor(tmp2.userName);
			tmp.isMentor = true;
			tmp.save();
			tmp2.addMentee(tmp.userName);
			tmp2.isMentee = true;
			tmp2.save();
		}

		String flash = flash("message");
		return ok(views.html.index.render("index", flash));
	}

    /**
     * Search page
     * @return SearchCriteria page loaded
     */
    public static Result searchCriteria(){
    	Form<User> aForm = form(User.class);
    	return ok(searchCriteria.render(aForm));
    }

    /**
     * Results page
     * @return SearchResults page loaded
     */
    public static Result searchResults(){
    	String aTitle = form().bindFromRequest().get("currentTitle");
    	String anEmployer = form().bindFromRequest().get("currentEmployer");
    	
    	return ok(searchResults.render(findByAll(aTitle, anEmployer), findByTitle(aTitle), 
    			findByEmployer(anEmployer)));
    }

    /**
     * Edit User Profile
     * @return EditProfiler page
     */
	public static Result editProfile() {
		Form<User> computerForm = Form.form(User.class);
		return ok(editProfile.render(computerForm));
	}

	/**
	 * View User edit profile
	 * 
	 * @param id
	 * @return ViewUserProfile
	 */
	public static Result viewUserProfile(long id) {
		User user = find().ref(id);
		return ok(viewUserProfile.render(user, Session.isSessionOwner(user)));
	}

	/**
	 * View user profile
	 * @param text user name
	 * @return UserProfile page
	 */
	public static Result userProfile(String text){
		if (text.equals("")){
			text = currentText;
			flash("message", "");
		} else{
			flash("message", "Welcome back to MentorWeb.");
		}
		User temp = new User(null,null);
		ArrayList<User> someMentors = new ArrayList<User>();
		ArrayList<User> someMentees = new ArrayList<User>();
		for (User element : find().findList()) {
			if (element.userName.equals(text))
				temp = element;
		}
		
		if ((temp.mentors != null)){
			temp.mentors = temp.mentors.replace("null", "");
			String[] mentors = temp.mentors.split(";");
			for (int i = 0; i < mentors.length; i++){
				for (User aUser : find().findList()) {
					if (aUser.userName.equals(mentors[i])){
						someMentors.add(aUser);
					}
				}
			}
		}
		if ((temp.mentees != null)){
			temp.mentees = temp.mentees.replace("null", "");
			String[] mentees = temp.mentees.split(";");
			for (int i = 0; i < mentees.length; i++){
				for (User aUser : find().findList()) {
					if (aUser.userName.equals(mentees[i])){
						someMentees.add(aUser);
					}
				}
			}
		}
			
		return ok(userProfile.render(flash("message"),temp, someMentors, someMentees));
	}
	/**
	 * render registration form page
	 */
	public static Result register() {
		return ok(views.html.register.render(flash("message"), userForm));
	}

	/**
	 * interpret registration form to create new User
	 */
	public static Result newUser() {
		Form<User> filledForm = userForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			flash("message", "Sorry, something broke. Please try again.");
			return badRequest(register.render(flash("message"),filledForm));
		} else {
			List<User> temp = find().findList();
			for (User aUser : temp) {
				if (aUser.userName.toLowerCase().equals((filledForm.data().get("userName").toLowerCase()))){
						flash("message", "Username is taken. Please try again.");
						return badRequest(register.render(flash("message"),filledForm));
				}
			}
			
			User.create(filledForm.get());
			currentText = filledForm.data().get("userName");
			session("connected", filledForm.data().get("userName"));
			flash("message", "User was successfully created.");
			return redirect(routes.UserApplication.index(""));
		}
	}

	/**
	 * Edit User profile
	 * 
	 * @param id
	 * @return Edit page
	 */
	public static Result edit(Long id) {
		User user = find().ref(id);
		if (Session.isSessionOwner(user)) {
			return ok(edit.render(userForm.fill(user), user));
		} else {
			return redirect(routes.UserApplication.index(""));
		}
	}

	/**
	 * Saves the form
	 * 
	 * @return redirected to user page
	 */
	public static Result save() {
		Form<User> boundForm = userForm.bindFromRequest();
		if (boundForm.hasErrors()) {
			return badRequest(editProfile.render(boundForm));
		}
		User aUser = boundForm.get();
		User.add(aUser);
		Ebean.save(User.users);
		return redirect(routes.UserApplication.editProfile());
	}
	
	public static Result show (long id){
		User user = find().ref(id);
		String flash = flash("message");
		return ok(show.render(flash, user, Session.isSessionOwner(user)));
	}
	
	/**
	 * Update user information
	 * 
	 * @param id
	 * @return
	 */
	public static Result update(Long id) {
		Form<User> filledForm = userForm.bindFromRequest();
		if(filledForm.hasErrors()){
			User user = find().ref(id);
			flash("message",
					"Sorry, unable to update your information. Please try again.");
			return badRequest(edit.render(userForm.fill(user), user));
		} else {
			filledForm.get().password = Session.encryptPassword(filledForm.get().password);
			filledForm.get().update(id);
			session("connected", filledForm.data().get("userName")); //gets user data
			flash("message", "Profile was successfully updated.");
			return redirect(routes.UserApplication.show(filledForm.get().id));
		}
	}
	
    /**
     * Finds all users in the database
     * @return list of users
     */
    public static Finder<Long, User> find() {
		return new Finder<Long, User>(Long.class, User.class);
	}
    
    /**
     * Find by user name
     * @param term
     * @return a user name profile information
     */
    public static User findByName(String term) {
		final List<User> results = find().findList();
		for (User candidate : results) {
			if (candidate.userName.toLowerCase().contains(term.toLowerCase())) {
				return candidate;
			}
		}
		return null;
	}
    
    /**
     * Find potential candidate with similar work title
     * @param aTitle candidate's title
     * @return a list of candidates with the title
     */
    public static List<User> findByTitle(String aTitle){
    	ArrayList<User> candidates = new ArrayList<User>();
    	for (User user : find().findList()) {
    		if (user.currentTitle != null)
    			for (String s: aTitle.split(" ")){
    				for (String text : user.currentTitle.split(" ")) {
    					if (text.toLowerCase().equals(s.toLowerCase()))
    						if (!candidates.contains(user))
								candidates.add(user);
    				}
    			}
		}
    	return candidates;
    }
    
    /**
     * Find potential candidate with similar employer
     * @param anEmployer the candidate's employer
     * @return a list of candidates with the employer
     */
    public static List<User> findByEmployer(String anEmployer){
    	ArrayList<User> candidates = new ArrayList<User>();
    	for (User user : find().findList()) {
    		if (user.currentEmployer != null){
	    		for (String s : anEmployer.split(" ")) {
		    			for (String text : user.currentEmployer.split(" ")) {
							if (text.toLowerCase().equals(s.toLowerCase()))
								if (!candidates.contains(user))
									candidates.add(user);
						}
	    		}
    		}
		}
    	return candidates;
    }
    
    /**
     * Find a perfect match
     * @param aTitle title of the candidate
     * @param anEmployer employer of the candidate
     * @return list of perfectly matched cadidates
     */
    public static List<User> findByAll(String aTitle, String anEmployer){
    	ArrayList<User> candidates = new ArrayList<User>();
    	
    	for (User user : find().findList()) {
    		if (user.currentTitle != null || user.currentEmployer != null){
    			if (user.currentTitle.toLowerCase().contains(aTitle.toLowerCase()) && user.currentEmployer.toLowerCase().equals(anEmployer.toLowerCase()))
    				candidates.add(user);
    		}
		}
    	return candidates;
    }
    
    /**
     * User becomes someone's mentor
     * @param aMentorName mentor name
     * @param aMenteeName mentee name
     */
    public static void beMentor(String aMentorName, String aMenteeName){
    	User mentor = new User(null,null);
    	User mentee = new User(null, null);
    	
    	for (User e : find().findList()) {
			if (e.userName.equals(aMentorName)){
				mentor = e;
				mentor.addMentee(aMenteeName);
				break;
			}
		}
    	
    	for (User e: find().findList()){
    		if (e.userName.equals(aMenteeName)){
				mentee = e;
				mentee.addMentor(aMentorName);
				break;
			}
    	}
    }
    
    /**
     * User becomes a mentee
     * @param aMenteeName mentor's anme
     * @param aMentorName mentee's name
     */
    public static void beMentee(String aMenteeName, String aMentorName){
    	User mentor = new User(null,null);
    	User mentee = new User(null, null);
    	
    	for (User e : find().findList()) {
			if (e.userName.equals(aMentorName)){
				mentor = e;
				mentor.addMentee(aMenteeName);
				break;
			}
		}
    	
    	for (User e: find().findList()){
    		if (e.userName.equals(aMenteeName)){
				mentee = e;
				mentee.addMentor(aMentorName);
				break;
			}
    	}
    }
    
    public static Finder<Long, User> aFind = new Finder<Long, User>(Long.class, User.class);
}

