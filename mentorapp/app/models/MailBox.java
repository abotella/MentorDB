package models;

/**
 * Mail box with methods toadd and retrieve messages
 */
import java.util.ArrayList;
import java.util.List;

public class MailBox {
	
	private int newMessageCount;
	private List<Message> messageQ;
	
	public MailBox() {
		newMessageCount = 0;
		messageQ = new ArrayList<Message>();
	}
	
	/**
	 * Adds a new message to the mail box, newest first, and update new unread message count
	 * @param msg message to be added to the mail box
	 */
	public void addMessage(Message msg) {
		newMessageCount++;
		messageQ.add(0, msg);
	}
	
	/**
	 * Retrieves the selected message from the list of messages in the mail box
	 * @param msgIndex  the index of the specified message
	 * @return the specified message
	 */
	public Message getMessage(int msgIndex) {
		Message msg = messageQ.get(msgIndex);
		if (msg.getIsNew()) {
			newMessageCount--;
			msg.setIsNew(false);
		}
		return msg;
	}
	
	/**
	 * Retrieves the current status of the mail box
	 * @return true if there are new messages, false otherwise
	 */
	public boolean getStatus() {
		return newMessageCount > 0;
	}
	
	/**
	 * Retrieves the number of new unread messages in the mail box
	 * @return the number of the unread messages.
	 */
	public int getNumNewMsg() {
		return newMessageCount;
	}
}
