package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.LogBean;

public class LogDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SELECT_LOGS = "SELECT * FROM log"; 
	
	public static ArrayList<LogBean> loadLogs() {
		ArrayList<LogBean> logs = new ArrayList<>();
		Connection connection = null;
		
		try {
			connection = connectionPool.checkOut();     
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, SELECT_LOGS, false);
	        ResultSet rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            int id = rs.getInt("log_id");
	            String poruka = rs.getString("poruka");
	            String datum = rs.getString("datum");
	            String logger = rs.getString("logger");
	            logs.add(new LogBean(id, poruka, datum, logger));
	        }
	        
	        pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return logs;
	}

}
