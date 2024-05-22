package savjetnikApp;

import java.io.Serializable;
import java.util.List;

public class SavjetnikBean implements Serializable {

	private static final long serialVersionUID = -3715032859354162631L;
	private int id;
	private String name, userName, password, email;
	private boolean verifikovan, loggedIn;
	private List<PorukaBean> primljenePoruke;

	public SavjetnikBean() {
	}
	
	public SavjetnikBean(int id, String name, String userName, String password, String email, boolean verifikovan) {
		super();
		this.id = id; this.name = name; this.userName = userName; this.password = password; this.email = email; this.verifikovan = verifikovan;
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

	public boolean isVerifikovan() {
		return verifikovan;
	}

	public void setVerifikovan(boolean verifikovan) {
		this.verifikovan = verifikovan;
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
	
	public List<PorukaBean> getPrimljenePoruke() {
		return primljenePoruke;
	}

	public void setPrimljenePoruke(List<PorukaBean> primljenePoruke) {
		this.primljenePoruke = primljenePoruke;
	}

	@Override
	public String toString() {
		return "SavjetnikBean [id=" + id + ", name=" + name + ", userName=" + userName + ", password=" + password
				+ ", email=" + email + ", verifikovan=" + verifikovan + ", loggedIn=" + loggedIn + ", primljenePoruke="
				+ primljenePoruke + "]";
	}

}
