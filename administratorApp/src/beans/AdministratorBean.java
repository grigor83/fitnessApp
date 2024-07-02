package beans;

import java.io.Serializable;

import dao.AdminDAO;
import dto.Administrator;

public class AdministratorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String username, password;
	private boolean isLoggedIn = false;
	
	public AdministratorBean() { }

	public boolean login(String username, String password) {
		Administrator admin = null;
		if ((admin = AdminDAO.selectByUsernameAndPassword(username, password)) != null) {
			id = admin.getId();
			username = admin.getUsername();
			password = admin.getPassword();
			isLoggedIn = true;
			return true;
		}
		return false;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
