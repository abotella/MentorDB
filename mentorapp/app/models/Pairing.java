package models;

import java.util.Calendar;

/**
 * Pairing class represents mentorship pairing of a mentor and a mentee with methods retrieve and set pairing attributes
 * 
 * @author 
 *
 */
public class Pairing {
	private User mentor;
	private User mentee;
	private Calendar pairingEnd;
	private Calendar pairingBegin;
	private int lengthInMonths;
	private boolean confirmed;
	
	
	/**
	 * Constructs a pairing object that represents a pairing proposal.
	 * @param aMentor mentor of the pairing
	 * @param aMentee mentee of the pairing
	 * @param numMonths the length of the pairing in months
	 */
	public Pairing (User aMentor, User aMentee, int numMonths) {
		mentor = aMentor;
		mentee = aMentee;
		lengthInMonths = numMonths;
		confirmed = false;
	}
	
	/**
	 * Retrieves the mentor participating in the pairing
	 * @return mentor in the pairing
	 */
	public User getMentor() {
		return mentor;
	}

	/**
	 * Set the mentor of the pairing to the specified mentor
	 * @param mentor the specified mentor
	 */
	public void setMentor(User mentor) {
		this.mentor = mentor;
	}

	/**
	 * Retrieves the mentee participating in the pairing
	 * @return mentee in the pairing
	 */
	public User getMentee() {
		return mentee;
	}

	/**
	 * Set the mentee of the pairing to the specified mentee
	 * @param mentee the specified mentee
	 */
	public void setMentee(User mentee) {
		this.mentee = mentee;
	}

	/**
	 * Retrieves the Calendar object containing current end time of the pairing
	 * @return the pairing end date Calendar object
	 */
	public Calendar getPairingEnd() {
		return pairingEnd;
	}

	/**
	 * 
	 * @param pairingEnd
	 */
	public void setPairingEnd(Calendar pairingEnd) {
		this.pairingEnd = pairingEnd;
	}

	/**
	 * Retrieves the Calendar object containing current beginning time of the pairing
	 * @return the pairing beginning date Calendar object
	 */
	public Calendar getPairingBegin() {
		return pairingBegin;
	}


	public void setPairingBegin(Calendar pairingBegin) {
		this.pairingBegin = pairingBegin;
	}


	public int getLengthInMonths() {
		return lengthInMonths;
	}


	public boolean getConfirmed() {
		return confirmed;
	}


	public void setLengthInMonths(int numMonths) {
		lengthInMonths = numMonths;
	}
	
	public void setConfirmed(boolean confirm) {
		confirmed = confirm;
	//	pairingBegin = Calendar.getInstance();
	//	pairingEnd = Calendar.getInstance();
	//	pairingEnd.add(Calendar.MONTH, lengthInMonths);
	}
	
	
}
