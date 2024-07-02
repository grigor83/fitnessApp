package beans;

import java.io.Serializable;
import java.util.ArrayList;

import dao.LogDAO;

public class LogBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String message, logDate, logger;
	private ArrayList<LogBean> logs = new ArrayList<>();


	public LogBean() {}

	public LogBean(int id, String message, String logDate, String logger) {
		super();
		this.id = id;
		this.message = message;
		this.logDate = logDate;
		this.logger = logger;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
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
