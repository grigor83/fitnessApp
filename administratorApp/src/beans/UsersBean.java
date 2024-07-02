package beans;

import java.io.Serializable;
import java.util.ArrayList;

import dto.User;

public class UsersBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<User> users;
	
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public boolean isUsernameDuplicate(String username) {
		User u = users.stream()
				 .filter(user -> user.getUsername().equals(username))
				 .findAny().orElse(null);
		if (u != null)
			return true;
		else
			return false;
	}
	
}
