package models;

/**
 * Message class representing a message between 2 users.
 * @author Team Nacho BizNess
 * @version 
 */
public class Message {
	private User sender;
	private User recipient;
	private String subject;
	private String messageBody;
	private boolean isNew;
	
	public Message(User sender, User recipient, String subject,
			String messageBody) {
		super();
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.messageBody = messageBody;
	}

	public User getSender() {
		return sender;
	}



	public User getRecipient() {
		return recipient;
	}


	public String getSubject() {
		return subject;
	}

	
	public String getMessageBody() {
		return messageBody;
	}

	
	public boolean getIsNew() {
		return isNew;
	}
	
	public void setIsNew(boolean read) {
		isNew = read;
	}
	
}