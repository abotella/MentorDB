package controllers;
/**
 * Action class for processing messages.
 */

import play.mvc.Controller;
import play.mvc.Result;

public class MessageActions extends Controller {
  
	/**
	 * Retrieves messages sent to specified receipient, most recent first
	 * 
	 * @return list of messages sent to receipient with sender, date, and subject
	 */
	public static Result list() {
		return TODO;
	}
  
	/**
	 * Retrieves the body of the selected message, and marks it as old message
	 * 
	 * @return the body of the selected message
	 */
	public static Result readMessage() {
		return TODO;
	}
	
	/**
	 * Creates a blank message form
	 * 
	 * @return blank message form
	 */
	public static Result newMessage() {
		return  TODO;
	}
  
	/**
	 * Sends the message to the specified receipient
	 * 
	 * @return confirmation for message sent
	 */
	public static Result send() {
		return TODO;
	}
  
	
	
}