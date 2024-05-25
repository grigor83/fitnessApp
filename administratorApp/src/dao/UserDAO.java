package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Category;
import dto.User;

public class UserDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SELECT_ALL_USERS = "SELECT * FROM korisnik";
	private static final String SELECT_USER = "SELECT * FROM korisnik WHERE korisnik_id = ?";
	private static final String INSERT_NEW_USER = "INSERT INTO korisnik (ime, grad, mejl, korisnicko_ime, lozinka, broj_kartice, savjetnik, verifikovan) "
												+ "VALUES (?,?,?,?,?,?,?,?)";
	private static final String UPDATE_USER = "UPDATE korisnik SET ime = ?, grad = ?, mejl = ?, korisnicko_ime = ?, lozinka = ?, broj_kartice = ?, "
												+ " savjetnik = ?, verifikovan = ? WHERE korisnik_id = ?";
	private static final String DELETE_USER = "DELETE FROM korisnik WHERE korisnik_id = ?";


	public static ArrayList<User> loadUsers() {
		ArrayList<User> users = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, SELECT_ALL_USERS, false);
			rs = pstmt.executeQuery();

			while (rs.next()) {
	            int id = rs.getInt("korisnik_id");
	            String ime = rs.getString("ime");
	            String grad = rs.getString("grad");
	            String mejl = rs.getString("mejl");
	            String username = rs.getString("korisnicko_ime");
	            String password = rs.getString("lozinka");
	            String brojKartice = rs.getString("broj_kartice");
	            boolean savjetnik = rs.getBoolean("savjetnik");
	            boolean verifikovan = rs.getBoolean("verifikovan");
	            users.add(new User(id, ime, grad, mejl, username, password, brojKartice, savjetnik, verifikovan));
	        }
			
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return users;
	}

	public static boolean deleteUser(int userId) {
		Connection connection = null;
		Object values[] = { userId };
		boolean result = false;
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, DELETE_USER, false, values);
			int rows = pstmt.executeUpdate();
			if (rows > 0)
				result = true;
			
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static User selectUser(int userId) {
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { userId };
		User selectedUser = null;
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, SELECT_USER, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("korisnik_id");
	            String ime = rs.getString("ime");
	            String grad = rs.getString("grad");
	            String mejl = rs.getString("mejl");
	            String username = rs.getString("korisnicko_ime");
	            String password = rs.getString("lozinka");
	            String brojKartice = rs.getString("broj_kartice");
	            boolean savjetnik = rs.getBoolean("savjetnik");
	            boolean verifikovan = rs.getBoolean("verifikovan");
	            selectedUser = new User(id, ime, grad, mejl, username, password, brojKartice, savjetnik, verifikovan);
			}

			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return selectedUser;
	}

	public static boolean updateUser(int userId, String ime, String grad, String email, String username, 
										String password, String brojKartice, boolean savjetnik, boolean verifikovan) {
		Connection connection = null;
		Object values[] = { ime, grad, email, username, password, brojKartice, savjetnik, verifikovan, userId };
		boolean result = false;
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, UPDATE_USER, false, values);
			int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) 
            	result = true;
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static boolean insertUser(String ime, String grad, String email, String username, String password,
			boolean savjetnik, boolean verifikovan, String brojKartice) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { ime, grad, email, username, password, brojKartice, savjetnik, verifikovan };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, INSERT_NEW_USER, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if (pstmt.getUpdateCount() > 0) {
				result = true;
			}
			/* Ako zelim da prilikom insertovanja u bazu odmah dobijem generisani id
			if (generatedKeys.next())
				user.setId(generatedKeys.getInt(1));
			*/
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
}
