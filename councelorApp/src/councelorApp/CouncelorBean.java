package councelorApp;

import java.io.Serializable;
import java.util.List;

public class CouncelorBean implements Serializable {

	private static final long serialVersionUID = -3715032859354162631L;
	private int id;
	private String name, userName, password, email;
	private boolean verified, loggedIn;
	private List<MessageBean> recievedMessages;

	public CouncelorBean() {
	}
	
	public CouncelorBean(int id, String name, String userName, String password, String email, boolean verifikovan) {
		super();
		this.id = id; this.name = name; this.userName = userName; this.password = password; this.email = email; this.verified = verifikovan;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<MessageBean> getRecievedMessages() {
		return recievedMessages;
	}

	public void setRecievedMessages(List<MessageBean> recievedMessages) {
		this.recievedMessages = recievedMessages;
	}

	@Override
	public String toString() {
		return "CouncelorBean [id=" + id + ", name=" + name + ", userName=" + userName + ", password=" + password
				+ ", email=" + email + ", verified=" + verified + ", loggedIn=" + loggedIn + ", recievedMessages="
				+ recievedMessages + "]";
	}

}
