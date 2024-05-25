package dto;

public class User {
	private int id;
	private String ime, grad, email, korisnickoIme, lozinka, brojKartice;
	private boolean savjetnik, verifikovan;

	public User() {	}

	public User(int id, String ime, String grad, String email, String korisnickoIme, String lozinka, String brojKartice, boolean savjetnik, boolean verifikovan) {
		super();
		this.id = id;
		this.ime = ime;
		this.grad = grad;
		this.email = email;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.brojKartice = brojKartice;
		this.savjetnik = savjetnik;
		this.verifikovan = verifikovan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getBrojKartice() {
		return brojKartice;
	}

	public void setBrojKartice(String brojKartice) {
		this.brojKartice = brojKartice;
	}

	public boolean isSavjetnik() {
		return savjetnik;
	}

	public void setSavjetnik(boolean savjetnik) {
		this.savjetnik = savjetnik;
	}

	public boolean isVerifikovan() {
		return verifikovan;
	}

	public void setVerifikovan(boolean verifikovan) {
		this.verifikovan = verifikovan;
	}

}
