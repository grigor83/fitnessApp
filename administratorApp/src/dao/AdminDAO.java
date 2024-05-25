package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Administrator;

// DAO is an abbreviation for Data Access Object, so it should encapsulate 
// the logic for retrieving, saving and updating data in your data storage 
// (a database, a file-system, whatever).
public class AdminDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SELECT = "SELECT * FROM administrator WHERE korisnicko_ime=? AND lozinka=?";
	private static final String IS_USERNAME_USED = "SELECT * FROM administrator WHERE korisnicko_ime= ?";
	private static final String INSERT = "INSERT INTO administrator (ime, prezime, korisnicko_ime, lozinka) VALUES (?,?,?,?)";
	
	public static Administrator selectByUsernameAndPassword(String username, String password){
		Administrator admin = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {username, password};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, SELECT, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				admin = new Administrator(rs.getInt("administrator_id"), rs.getString("ime"), rs.getString("prezime"), 
										  rs.getString("korisnicko_ime"), rs.getString("lozinka"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return admin;
	}
	
	public static boolean isUserNameUsed(String username) {
		boolean result = true;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {username};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, IS_USERNAME_USED, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				result = false;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	public static boolean insert(Administrator admin) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { admin.getIme(), admin.getPrezime(), admin.getKorisnickoIme(), admin.getLozinka() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, INSERT, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			if (generatedKeys.next())
				admin.setId(generatedKeys.getInt(1));
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

}
