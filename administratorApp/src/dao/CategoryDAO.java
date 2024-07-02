package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Atribut;
import dto.Category;

public class CategoryDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM category";
	private static final String SELECT_CATEGORY = "SELECT * FROM category WHERE id = ?";
	private static final String INSERT_NEW_CATEGORY = "INSERT INTO category (category_name, attribute_id) VALUES (?,?)";
	private static final String UPDATE_CATEGORY = "UPDATE category SET category_name = ?, attribute_id = ? WHERE id = ?";
	private static final String DELETE_CATEGORY = "DELETE FROM category WHERE id = ?";
	private static final String SELECT_ALL_ATTRIBUTES = "SELECT * FROM attribute";
	private static final String SELECT_ATTRIBUTE = "SELECT * FROM attribute WHERE id=?";
	private static final String INSERT_NEW_ATTRIBUTE = "INSERT INTO attribute (attribute_name) VALUES (?)";
	
	public static ArrayList<Category> loadCategories() {
		ArrayList<Category> categories = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, SELECT_ALL_CATEGORIES, false);
			rs = pstmt.executeQuery();

			while (rs.next()) {
	            int id = rs.getInt("id");
	            String ime = rs.getString("category_name");
	            Object values[] = { rs.getInt("attribute_id") };
	            pstmt = ConnectionPool.prepareStatement(connection, SELECT_ATTRIBUTE, false, values);
	            ResultSet rs2 = pstmt.executeQuery();
	            if (rs2.next())
	            	categories.add(new Category(id, ime, rs2.getInt("id"), rs2.getString("attribute_name")));
	        }
			
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return categories;
	}
	
	public static Category loadCategory(int categoryId) {
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { categoryId };
		Category selectedCategory = null;
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, SELECT_CATEGORY, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
	            String ime = rs.getString("category_name");
	            Object values2[] = { rs.getInt("attribute_id") };
	            pstmt = ConnectionPool.prepareStatement(connection, SELECT_ATTRIBUTE, false, values2);
	            ResultSet rs2 = pstmt.executeQuery();
	            if (rs2.next())
	            	selectedCategory = new Category(id, ime, rs2.getInt("id"), rs2.getString("attribute_name"));
			}

			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return selectedCategory;
	}
	
	public static boolean deleteCategory(int id) {
		Connection connection = null;
		Object values[] = { id };
		boolean result = false;
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, DELETE_CATEGORY, false, values);
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

	public static boolean updateCategory(int categoryId, String nazivKategorije, int atributId) {
		Connection connection = null;
		Object values[] = { nazivKategorije, atributId, categoryId };
		boolean result = false;
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, UPDATE_CATEGORY, false, values);
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

	public static boolean insertCategory(String nazivKategorije, String atribut_id) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { nazivKategorije, atribut_id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, INSERT_NEW_CATEGORY, true, values);
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
	
	public static ArrayList<Atribut> loadAtributes() {
		ArrayList<Atribut> atributes = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, SELECT_ALL_ATTRIBUTES, false);
			rs = pstmt.executeQuery();

			while (rs.next()) {
	            int id = rs.getInt("id");
	            String ime = rs.getString("attribute_name");
	            atributes.add(new Atribut(id, ime));
	        }
			
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return atributes;
	}

	public static boolean insertAttribute(String nazivAtributa) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { nazivAtributa };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ConnectionPool.prepareStatement(connection, INSERT_NEW_ATTRIBUTE, true, values);
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
