package beans;

import java.io.Serializable;

import dao.LogDAO;

public class LogBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id, brojLogova;

	public LogBean() {}

	public LogBean(int id, int brojLogova) {
		super();
		this.id = id;
		this.brojLogova = brojLogova;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBrojLogova() {
		return brojLogova;
	}

	public void setBrojLogova(int brojLogova) {
		this.brojLogova = brojLogova;
	}
	
	public void loadLogs() {
		LogBean lb = LogDAO.loadLogs();
		this.id = lb.getId();
		this.brojLogova = lb.getBrojLogova();
	}
	
	public void resetLogs() {
		LogBean lb = LogDAO.resetLogs();
		this.id = lb.getId();
		this.brojLogova = lb.getBrojLogova();
		System.out.println(this.brojLogova);
	}

}
