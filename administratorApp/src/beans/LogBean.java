package beans;

import java.io.Serializable;
import java.util.ArrayList;

import dao.LogDAO;

public class LogBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String poruka, datum, logger;
	private ArrayList<LogBean> logs = new ArrayList<>();


	public LogBean() {}

	public LogBean(int id, String poruka, String datum, String logger) {
		super();
		this.id = id;
		this.poruka = poruka;
		this.datum = datum;
		this.logger = logger;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPoruka() {
		return poruka;
	}

	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getLogger() {
		return logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}

	public ArrayList<LogBean> getLogs() {
		return logs;
	}

	public void setLogs(ArrayList<LogBean> logs) {
		this.logs = logs;
	}

	public void loadLogs() {
		logs = LogDAO.loadLogs();
	}

}
