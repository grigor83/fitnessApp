package dto;

public class User {
	private int id;
	private String first_name, city, email, username, password, cardNumber;
	private boolean councelor, verified;

	public User() {	}

	public User(int id, String firstName, String city, String email, String username, String password,
			String cardNumber, boolean councelor, boolean verified) {
		super();
		this.id = id;
		this.first_name = firstName;
		this.city = city;
		this.email = email;
		this.username = username;
		this.password = password;
		this.cardNumber = cardNumber;
		this.councelor = councelor;
		this.verified = verified;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public boolean isCouncelor() {
		return councelor;
	}

	public void setCouncelor(boolean councelor) {
		this.councelor = councelor;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

}
