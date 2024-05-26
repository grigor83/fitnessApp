package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.LogBean;

public class LogDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SELECT_LOG = "SELECT * FROM log WHERE log_id = ?"; 
	private static final String UPDATE_LOG = "UPDATE log SET broj_logova = ? WHERE log_id = ?"; 
	
	public static LogBean loadLogs() {
		Connection connection = null;
		LogBean logBean = null;
		
		try {
			connection = connectionPool.checkOut();     
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, SELECT_LOG, false, 1);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	        	int logs = rs.getInt("broj_logova");
	        	logs = logs;
	        	Object values[] = { logs, 1 };
	        	
	        	pstmt = ConnectionPool.prepareStatement(connection, UPDATE_LOG, false, values);
		        if (pstmt.executeUpdate() > 0)
		        	logBean = new LogBean(1, logs);
	        }	        
	        
	        pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return logBean;
	}

	public static LogBean resetLogs() {
		Connection connection = null;
		LogBean logBean = null;
    	Object values[] = { 0, 1 };
		
		try {
			connection = connectionPool.checkOut();     
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, UPDATE_LOG, false, values);
		    if (pstmt.executeUpdate() > 0)
		       logBean = new LogBean(1,0);
	        
	        pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return logBean;
	}

}
