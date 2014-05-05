package controllers;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.List;

import play.*;
import play.mvc.*;
import play.db.*;
import play.data.*;
import play.data.validation.Constraints;
import scala.collection.parallel.ParIterableLike.Find;
import static play.data.Form.*;
import models.*;

public class Session extends Controller {
	
	public static class Login {

		@Constraints.Required (message = "Username is required!")
		public String userName;
		
		@Constraints.Required (message = "Password is required!")
		public String password;

		public String validate() {
			if (User.authenticate(userName, password) == null) {
				return "Invalid user or password";
			}
			return null;
		}
	}

	/**
	 * Encrypts user password
	 * 
	 * @param cleanPassword
	 * @return hashed password
	 */
	public static String encryptPassword(String cleanPassword) {
		if (cleanPassword == null)
			return null;
		return BCrypt.hashpw(cleanPassword, BCrypt.gensalt());
	}

	public static boolean checkPassword(String applicant, String encrypted) {
		if (applicant == null || encrypted == null)
			return false;
		return BCrypt.checkpw(applicant, encrypted);
	}

	public static Result login() {
		return ok(views.html.login.render(flash("message"), form(Login.class)));
	}

	public static Result authenticate() {
		Form<Login> loginForm = form(Login.class).bindFromRequest();
		
		if (loginForm.hasErrors()) {
			flash("message", "Invalid username or password.");
			return redirect(routes.Session.login());
		} else {
			//Test
			String uName = "";
			List<User> temp = UserApplication.find().findList();
			for (User aUser : temp) {
				if (aUser.userName.equals(loginForm.data().get("userName"))){
					uName = aUser.userName;
					UserApplication.currentText = uName;
					break;
				}
			}
			
			session("connected", loginForm.get().userName);
			return redirect(routes.UserApplication.userProfile(uName));
		}
	}

	public static Result logout() {
		session().clear();
		flash("message", "You've been logged out");
		return redirect(routes.UserApplication.index(""));
	}

	public static boolean isSessionOwner(User user) {
		if (user.userName.equals(session("connected")))
			return true;
		else
			return false;
	}

}