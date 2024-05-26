package savjetnikApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;
import javax.activation.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class SavjetnikService {
	
	private static List<SavjetnikBean> savjetnici;
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

	public SavjetnikService() { }
	
	public static List<SavjetnikBean> getSavjetnici() {
		return savjetnici;
	}

	public static void setSavjetnici(List<SavjetnikBean> savjetnici) {
		SavjetnikService.savjetnici = savjetnici;
	}
	
	public static boolean insertSavjetnik(SavjetnikBean newSavjetnik) throws IOException, SQLException, ClassNotFoundException {	
		boolean result = false;
		Connection connection = null;

		try {
			connection = connectionPool.checkOut();
			String insertSQL = "INSERT INTO korisnik (ime, korisnicko_ime, lozinka, mejl, grad, broj_kartice, verifikovan, savjetnik) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
	        preparedStatement.setString(1, newSavjetnik.getName());
	        preparedStatement.setString(2, newSavjetnik.getUserName());
	        preparedStatement.setString(3, newSavjetnik.getPassword());
	        preparedStatement.setString(4, newSavjetnik.getEmail());
	        preparedStatement.setString(5, "Banjaluka");
	        preparedStatement.setString(6, "000");
	        preparedStatement.setBoolean(7, false);
	        preparedStatement.setBoolean(8, true);
	        
	        int rowsInserted = preparedStatement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("A new user was inserted successfully!");
	            result = true;
	        }
	        else 
	        	result = false;
            preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return result;		
		
		/*
		Connection connection = DatabaseConnection.getConnection();
        System.out.println("Database connection successful!");
        String insertSQL = "INSERT INTO korisnik (ime, korisnicko_ime, lozinka, mejl, grad, broj_kartice, verifikovan, savjetnik) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
        preparedStatement.setString(1, newSavjetnik.getName());
        preparedStatement.setString(2, newSavjetnik.getUserName());
        preparedStatement.setString(3, newSavjetnik.getPassword());
        preparedStatement.setString(4, newSavjetnik.getEmail());
        preparedStatement.setString(5, "Banjaluka");
        preparedStatement.setString(6, "000");
        preparedStatement.setBoolean(7, false);
        preparedStatement.setBoolean(8, true);
        
        int rowsInserted = preparedStatement.executeUpdate();
        connection.close();

        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");
            return true;
        }
        else 
        	return false;
		*/
		
		//////////////////////////////////////////////////////////////////////////////////
		 /*
		 * Ovaj kod je za slanje http zahtjeva za kreiranje korisnika springboot serveru.
		 * JSONObject jsonObject = new JSONObject();
        jsonObject.put("ime", newSavjetnik.getName());
        jsonObject.put("korisnickoIme", newSavjetnik.getUserName());
        jsonObject.put("lozinka", newSavjetnik.getPassword());
        jsonObject.put("mejl", newSavjetnik.getEmail());
        jsonObject.put("grad", "Banjaluka");
        jsonObject.put("brojKartice", "000");
        jsonObject.put("savjetnik", "true");
        
		URL url = new URL("http://localhost:8080/korisnik");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        
        try (DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream())) {
            outputStream.writeBytes(jsonObject.toString());
        }
        // Get the response
        int code = conn.getResponseCode();
        conn.disconnect();

        if (code == 201)
        	return true;
        else
        	return false;
		 */
	}
	
	public static void loadUsers() throws IOException, ClassNotFoundException, SQLException {
		savjetnici = new ArrayList<>();

		Connection connection = null;
		try {
			connection = connectionPool.checkOut();
			String selectSQL = "SELECT korisnik_id, ime, korisnicko_ime, lozinka, mejl, verifikovan, savjetnik FROM korisnik";
	        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            int id = resultSet.getInt("korisnik_id");
	            String ime = resultSet.getString("ime");
	            String korisnickoIme = resultSet.getString("korisnicko_ime");
	            String lozinka = resultSet.getString("lozinka");
	            String email = resultSet.getString("mejl");
	            boolean verifikovan = resultSet.getBoolean("verifikovan");
	            boolean savjetnik = resultSet.getBoolean("savjetnik");
	            
	            if (savjetnik)
	            	savjetnici.add(new SavjetnikBean(id, ime, korisnickoIme, lozinka, email, verifikovan));
	        }
            preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		/*
		Connection connection = DatabaseConnection.getConnection();
        System.out.println("Database connection successful!");
        String selectSQL = "SELECT korisnik_id, ime, korisnicko_ime, lozinka, mejl, verifikovan, savjetnik FROM korisnik";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("korisnik_id");
            String ime = resultSet.getString("ime");
            String korisnickoIme = resultSet.getString("korisnicko_ime");
            String lozinka = resultSet.getString("lozinka");
            String email = resultSet.getString("mejl");
            boolean verifikovan = resultSet.getBoolean("verifikovan");
            boolean savjetnik = resultSet.getBoolean("savjetnik");
            
            if (savjetnik)
            	savjetnici.add(new SavjetnikBean(id, ime, korisnickoIme, lozinka, email, verifikovan));
        }
        
        connection.close();
		*/
		////////////////////////////////////////////////////////////
		/* Ovaj kod je za slanje get zahtjeva springboot serveru.
		URL url = new URL("http://localhost:8080/korisnik");
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    // Read response
	    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String inputLine;
	    StringBuffer data = new StringBuffer();
	    while ((inputLine = in.readLine()) != null) {
	        data.append(inputLine);
	    }
	    in.close();
	    conn.disconnect();
	    
	 // Parse JSON response
	    JSONArray jsonArray = new JSONArray(data.toString());
	    for (int i = 0; i < jsonArray.length(); i++) {
	        JSONObject jsonObject = jsonArray.getJSONObject(i);
	        if (jsonObject.getBoolean("savjetnik")) {
	        	savjetnici.add(new SavjetnikBean(jsonObject.getInt("korisnikId"), jsonObject.getString("ime"), 
	        					jsonObject.getString("korisnickoIme"), jsonObject.getString("lozinka"), jsonObject.getString("mejl")));
	        }
	    }
		 * 
		 */
	}
	
	public static SavjetnikBean login(String userName, String password) throws IOException, ClassNotFoundException, SQLException {	
		SavjetnikService.loadUsers();
		SavjetnikBean s = savjetnici.stream()
						 .filter(savjetnik -> savjetnik.getUserName().equals(userName) && savjetnik.getPassword().equals(password)
								 && savjetnik.isVerifikovan())
						 .findAny().orElse(null);
		if (s != null)
			s.setPrimljenePoruke(getUsersMessages(s));
		
		return s;
	}
	
	public static void updateLogs() {
		Connection connection = null;
		String selectSQL = "SELECT * FROM log WHERE log_id = ?";        
		String updateSQL = "UPDATE log SET broj_logova = ? WHERE log_id = ?"; 
		try {
			connection = connectionPool.checkOut();       
	        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setInt(1, 1);
	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	        	int logs = rs.getInt("broj_logova");
	        	preparedStatement = connection.prepareStatement(updateSQL);
	        	preparedStatement.setInt(1,logs+1);
		        preparedStatement.setInt(2,1);
		        preparedStatement.executeUpdate();
	        }
	        
	        preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
	}
	
	public static ArrayList<PorukaBean> getUsersMessages(SavjetnikBean savjetnik) throws IOException, ClassNotFoundException, SQLException{
    	ArrayList<PorukaBean> temp = new ArrayList<>();
    	
    	Connection connection = null;
		try {
			connection = connectionPool.checkOut();
			String selectSQL = "SELECT poruka_id, posiljalac_id, datum_slanja, tekst, procitana FROM poruka WHERE primalac_id = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setInt(1, savjetnik.getId());      
	        ResultSet resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            int poruka_id = resultSet.getInt("poruka_id");
	            int posiljalac_id = resultSet.getInt("posiljalac_id");
	            String datum = resultSet.getString("datum_slanja");
	            String tekst = resultSet.getString("tekst");
	            boolean procitana = resultSet.getBoolean("procitana");
	            
	            String selectPosiljalac = "SELECT korisnik_id, ime, korisnicko_ime, lozinka, mejl, verifikovan FROM korisnik WHERE korisnik_id = ?";
	            preparedStatement = connection.prepareStatement(selectPosiljalac);
	            preparedStatement.setInt(1, posiljalac_id);
	            ResultSet resultSet2 = preparedStatement.executeQuery();
	            if (resultSet2.next()) {
	            	SavjetnikBean posiljalac = new SavjetnikBean(resultSet2.getInt("korisnik_id"), resultSet2.getString("ime"), resultSet2.getString("korisnicko_ime"), 
	        				null, resultSet2.getString("mejl"), resultSet2.getBoolean("verifikovan"));
	            	temp.add(new PorukaBean(poruka_id, posiljalac, tekst,
	    					datum, procitana));
	            }
	            preparedStatement.close();
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return temp;

    	/*
		Connection connection = DatabaseConnection.getConnection();
        System.out.println("Database connection successful!");
        String selectSQL = "SELECT poruka_id, posiljalac_id, datum_slanja, tekst, procitana FROM poruka WHERE primalac_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, savjetnik.getId());

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int poruka_id = resultSet.getInt("poruka_id");
            int posiljalac_id = resultSet.getInt("posiljalac_id");
            String datum = resultSet.getString("datum_slanja");
            String tekst = resultSet.getString("tekst");
            boolean procitana = resultSet.getBoolean("procitana");
            
            String selectPosiljalac = "SELECT korisnik_id, ime, korisnicko_ime, lozinka, mejl, verifikovan FROM korisnik WHERE korisnik_id = ?";
            preparedStatement = connection.prepareStatement(selectPosiljalac);
            preparedStatement.setInt(1, posiljalac_id);
            ResultSet resultSet2 = preparedStatement.executeQuery();
            if (resultSet2.next()) {
            	SavjetnikBean posiljalac = new SavjetnikBean(resultSet2.getInt("korisnik_id"), resultSet2.getString("ime"), resultSet2.getString("korisnicko_ime"), 
        				null, resultSet2.getString("mejl"), resultSet2.getBoolean("verifikovan"));
            	temp.add(new PorukaBean(poruka_id, posiljalac, tekst,
    					datum, procitana));
            }
        }
        
        connection.close();
        return temp;
        */
    	//////////////////////////////////////////////////////////////////////////
		/*
		URL url = new URL("http://localhost:8080/poruka/user/" + savjetnik.getId());
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    // Read response
	    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String inputLine;
	    StringBuffer data = new StringBuffer();
	    while ((inputLine = in.readLine()) != null) {
	        data.append(inputLine);
	    }
	    in.close();
	    int code = conn.getResponseCode();
        conn.disconnect();
	   
    	ArrayList<PorukaBean> temp = new ArrayList<>();
	    if (data.length() > 0) {
	    	// Parse JSON response
		    JSONArray jsonArray = new JSONArray(data.toString());
		    for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject jsonObject = jsonArray.getJSONObject(i);
		        temp.add(new PorukaBean(jsonObject.getInt("porukaId"), jsonObject.getJSONObject("posiljalac"), jsonObject.getString("tekst"),
		        					jsonObject.getString("datum"), jsonObject.getBoolean("procitana")));
		    }
	    }
	    
	    return temp;
	    */
	}
	
	public static boolean isUsernameDuplicate(String username) {
		ArrayList<SavjetnikBean> korisnici = new ArrayList<>();

		Connection connection = null;
		try {
			connection = connectionPool.checkOut();
			String selectSQL = "SELECT korisnik_id, ime, korisnicko_ime, lozinka, mejl, verifikovan, savjetnik FROM korisnik";
	        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            int id = resultSet.getInt("korisnik_id");
	            String ime = resultSet.getString("ime");
	            String korisnickoIme = resultSet.getString("korisnicko_ime");
	            String lozinka = resultSet.getString("lozinka");
	            String email = resultSet.getString("mejl");
	            boolean verifikovan = resultSet.getBoolean("verifikovan");	            
	            korisnici.add(new SavjetnikBean(id, ime, korisnickoIme, lozinka, email, verifikovan));
	        }
            preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		SavjetnikBean s = korisnici.stream()
				 .filter(user -> user.getUserName().equals(username))
				 .findAny().orElse(null);
		if (s != null)
			return true;
		else
			return false;
	}
	
	public static void updateMessage(SavjetnikBean savjetnik, PorukaBean message) throws IOException, ClassNotFoundException, SQLException {	
		Connection connection = null;
		try {
			connection = connectionPool.checkOut();
			String updateSQL = "UPDATE poruka SET procitana = ? WHERE poruka_id = ?";        
	        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
	        preparedStatement.setBoolean(1, true);
	        preparedStatement.setInt(2, message.getId());

	        int rowsUpdated = preparedStatement.executeUpdate();
	        if (rowsUpdated > 0) {
	        	PorukaBean p = savjetnik.getPrimljenePoruke().stream()
	      				 .filter(poruka -> poruka.getId() == message.getId())
	      				 .findAny().orElse(null);
	           	if (p != null)
	             		p.setProcitana(true);
	        } 
	        preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		/*
		Connection connection = DatabaseConnection.getConnection();
        System.out.println("Database connection successful!");
        String updateSQL = "UPDATE poruka SET procitana = ? WHERE poruka_id = ?";        
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
        preparedStatement.setBoolean(1, true);
        preparedStatement.setInt(2, message.getId());

        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
        	PorukaBean p = savjetnik.getPrimljenePoruke().stream()
      				 .filter(poruka -> poruka.getId() == message.getId())
      				 .findAny().orElse(null);
           	if (p != null)
             		p.setProcitana(true);
        } 
        */
		///////////////////////////////////////////////////////////
		/*
		URL url = new URL("http://localhost:8080/poruka/setProcitana/" + message.getId());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        
        String data = "{\"procitana\": \"true\"}";
        // Send the request
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.writeBytes(data);
            wr.flush();
        }
     // Get the response
        int code = conn.getResponseCode();
        conn.disconnect();
        if (code == 200) {
        	PorukaBean p = savjetnik.getPrimljenePoruke().stream()
   				 .filter(poruka -> poruka.getId() == message.getId())
   				 .findAny().orElse(null);
        	if (p != null)
          		p.setProcitana(true);
        }
        */
	}
	
	public static boolean sendEmail(String primalac, String naslov, String poruka) {
		String SMTP_HOST_NAME = "smtp.gmail.com"; 
	    String SMTP_AUTH_USER = "igor.grubisa@student.etf.unibl.org";
	    String SMTP_AUTH_PWD = "wbzp toht sgsj jxkq";
		
		Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_AUTH_USER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(primalac));
            message.setSubject(naslov);
            message.setText(poruka);

            Transport.send(message);
            System.out.println("Email sent successfully");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public static void sendEmailWithAttachements(ArrayList<String> params) {	    
	    String SMTP_HOST_NAME = "smtp.gmail.com"; 
	    String SMTP_AUTH_USER = "igor.grubisa@student.etf.unibl.org";
	    String SMTP_AUTH_PWD = "wbzp toht sgsj jxkq";
		
		Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
            }
        });
        
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(SMTP_AUTH_USER));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(params.get(0)));
            msg.setSubject(params.get(1));

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(params.get(2));
            // Create a multipart message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            String fileName = params.get(3);
            if (fileName != "") {
            	// Part two is attachment
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(fileName);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(fileName);
                /*
                String ext = fileName.split("\\.")[1];
                if (ext.equals("txt"))
                	messageBodyPart.setHeader("Content-Type", "text/plain");
                else if (ext.equals("pdf"))
                		messageBodyPart.setHeader("Content-Type", "application/pdf");
                	else 
                		messageBodyPart.setHeader("Content-Type", "image/" + ext);
                		*/
                multipart.addBodyPart(messageBodyPart);
            }

            msg.setContent(multipart);
            Transport.send(msg);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	
	public static ArrayList<String> getParametersFromRequest(HttpServletRequest request) throws FileUploadException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
 	    ServletFileUpload upload = new ServletFileUpload(factory);
 	    
	    String primalac = "";
	    String naslov = "";
	    String poruka = "";
	    String fileName = "";
	    
 	    List<FileItem> items = upload.parseRequest(request);
	    for(FileItem item : items) {
	        	if (item.isFormField()) {
	        		// Process regular form fields
	               String fieldName = item.getFieldName();
	               String fieldValue = item.getString();
	               if (fieldName.equals("primalac"))
	                        primalac = fieldValue;
	               else if (fieldName.equals("naslov")) 
	                        naslov = fieldValue;
	               		else if (fieldName.equals("poruka"))
	               			poruka = fieldValue;
	        	}
	        	else {
	        		fileName = item.getName();
	        	}
	    }
	    
	    return new ArrayList<>(List.of(primalac,naslov, poruka, fileName));
	}

}
