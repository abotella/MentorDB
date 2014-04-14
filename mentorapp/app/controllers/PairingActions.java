package controllers;

import play.mvc.Controller;
import play.*;
import play.data.Form;
import play.mvc.Result;
import views.html.*;


/**
 * Utility class for manipulating Pairing objects
 * 
 * @author Team Nacho BizNess
 *
 */
public class PairingActions extends Controller {

	/**
	 * Lists all mentor/mentee pairings the user is participating
	 * 
	 * @return
	 */
	public static Result list() {
		return TODO;
	}
	
	/**
	 * Initiate a new pairing with a potential participant
	 * 
	 * @return
	 */
	public static Result initiate() {
		return TODO;
	}
	
	/**
	 * Edit an existing pairing
	 * 
	 * @return
	 */
	public static Result editPairing() {
		return TODO;
	}
	
	/**
	 * Accept current terms in the proposed pairing
	 * 
	 * @return
	 */
	public static Result acceptPairing() {
		return TODO;
	}
	
	/**
	 * Terminate proposed pairing process
	 * 
	 * @return
	 */
	public static Result rejectPairing() {
		return TODO;
	}
	
	/**
	 * Terminate an active pairing
	 * 
	 * @return
	 */
	public static Result terminatePairing() {
		return TODO;
	}
}
