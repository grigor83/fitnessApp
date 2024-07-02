package councelorApp;

import java.io.Serializable;
import org.json.JSONObject;

public class MessageBean implements Serializable {

	private static final long serialVersionUID = -6222944152142327826L;
	private int id;
	private CouncelorBean sender;
	private String content, sentDate;
	private boolean seen;

	public MessageBean() {
		
	}
	

	public MessageBean(int id, CouncelorBean sender, String content, String sentDate, boolean seen) {
		super();
		this.id = id;
		this.sender = sender;
		this.content = content;
		this.sentDate = sentDate;
		this.seen = seen;
	}



	public MessageBean(int id, JSONObject jsonObject, String content, String sentDate, boolean seen) {
		super();
		this.id = id;
		this.sender = new CouncelorBean(jsonObject.getInt("korisnikId"), jsonObject.getString("ime"), 
				jsonObject.getString("korisnickoIme"), null, jsonObject.getString("mejl"), jsonObject.getBoolean("verifikovan"));
		this.content = content;
		this.sentDate = sentDate;
		this.seen = seen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public CouncelorBean getSender() {
		return sender;
	}


	public void setSender(CouncelorBean sender) {
		this.sender = sender;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getSentDate() {
		return sentDate;
	}


	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}


	public boolean isSeen() {
		return seen;
	}


	public void setSeen(boolean seen) {
		this.seen = seen;
	}

}
