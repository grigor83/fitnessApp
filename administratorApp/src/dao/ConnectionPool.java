package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Vector;

public class ConnectionPool {
	private String jdbcURL;
	private String username;
	private String password;
	private int preconnectCount;
	private int connectCount;
	private int maxIdleConnections;
	private int maxConnections;
	private Vector<Connection> usedConnections;
	private Vector<Connection> freeConnections;
	
	private static ConnectionPool connectionPool;
	
	static {
	    ResourceBundle bundle = PropertyResourceBundle.getBundle("dao.ConnectionPool");
	    String jdbcURL = bundle.getString("jdbcURL");
	    String username = bundle.getString("username");
	    String password = bundle.getString("password");
	    String driver = bundle.getString("driver");
	    int preconnectCount = 0;
	    int maxIdleConnections = 10;
	    int maxConnections = 10;
	    try {
	        Class.forName(driver);
	      preconnectCount = Integer.parseInt(
	        bundle.getString("preconnectCount"));
	      maxIdleConnections = Integer.parseInt(
	        bundle.getString("maxIdleConnections"));
	      maxConnections = Integer.parseInt(
	        bundle.getString("maxConnections"));
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	    try {
	      connectionPool = new ConnectionPool(
	        jdbcURL, username, password,
	        preconnectCount, maxIdleConnections,
	        maxConnections);
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	  }

	protected ConnectionPool(String aJdbcURL, String aUsername,
		    String aPassword, int aPreconnectCount,
		    int aMaxIdleConnections,
		    int aMaxConnections)
		    throws ClassNotFoundException, SQLException {

		    freeConnections = new Vector<Connection>();
		    usedConnections = new Vector<Connection>();
		    jdbcURL = aJdbcURL;
		    username = aUsername;
		    password = aPassword;
		    preconnectCount = aPreconnectCount;
		    maxIdleConnections = aMaxIdleConnections;
		    maxConnections = aMaxConnections;

		    for (int i = 0; i < preconnectCount; i++) {
		      Connection conn = DriverManager.getConnection(
		        jdbcURL, username, password);
		      conn.setAutoCommit(true);
		      freeConnections.addElement(conn);
		    }
		    connectCount = preconnectCount;
	}
	
	public synchronized Connection checkOut()
		    throws SQLException {

		    Connection conn = null;
		    if (freeConnections.size() > 0) {
		      conn = (Connection)freeConnections.elementAt(0);
		      freeConnections.removeElementAt(0);
		      usedConnections.addElement(conn);
		    } else {
		      if (connectCount < maxConnections) {
		        conn = DriverManager.getConnection(
		          jdbcURL, username, password);
		        usedConnections.addElement(conn);
		        connectCount++;
		      } else {
		        try {
		          wait();
		          conn = (Connection)freeConnections.elementAt(0);
		          freeConnections.removeElementAt(0);
		          usedConnections.addElement(conn);
		        } catch (InterruptedException ex) {
		          ex.printStackTrace();
		        }
		      }
		    }
		    return conn;
	}
	
	public synchronized void checkIn(Connection aConn) {
	    if (aConn ==  null)
	      return;
	    if (usedConnections.removeElement(aConn)) {
	      freeConnections.addElement(aConn);
	      while (freeConnections.size() > maxIdleConnections) {
	        int lastOne = freeConnections.size() - 1;
	        Connection conn = (Connection)
	          freeConnections.elementAt(lastOne);
	        try { conn.close(); } catch (SQLException ex) { }
	        freeConnections.removeElementAt(lastOne);
	      }
	      notify();
	    }
	}
	
	public static ConnectionPool getConnectionPool() {
	    return connectionPool;
	}
	
	public static PreparedStatement prepareStatement(Connection connection, String sql, boolean returnGeneratedKeys, Object... values)
			throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql,
				returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS
						: Statement.NO_GENERATED_KEYS);
		setValues(preparedStatement, values);
		return preparedStatement;
	}

	public static void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException {
		for (int i = 0; i < values.length; i++) {
			preparedStatement.setObject(i + 1, values[i]);
		}
	}
	
	public static PreparedStatement prepareStatement(Connection connection, String sql, boolean returnGeneratedKeys)
			throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql,
				returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS
						: Statement.NO_GENERATED_KEYS);
		return preparedStatement;
	}

}
