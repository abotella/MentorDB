package controllers;

import java.util.Set;

import models.User;
//import play.api.data.*;
import play.data.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Users extends Controller {
	private static final Form<User> userForm = Form.form(User.class);
	
	public static Result list() {
		Set<User> users = User.findAll();
		return ok(list.render(users));
	}

	public static Result save() {
		return ok();
	}

	public static Result show(Long ean) {
		return ok();
	}

	public static Result showBlank() {
		return ok(show.render(userForm));
	}
}
