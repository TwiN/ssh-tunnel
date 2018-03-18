package org.twinnation.sshtunnel;

import java.io.File;
import java.sql.*;


public class Example {
	
	public static void main(String[] args) throws SQLException {
		TunnelConfiguration config = new TunnelConfiguration();
		config.setServerIp("12.34.56.78");
		config.setServerPort(3306); // the port the tunnel will lead you to (End of tunnel)
		config.setLocalPort(3366); // The port you will connect from in order to access the server (Start of tunnel)
		config.setSshUsername("root");
		
		// Using a SSH key
		config.setSshPrivateKey(new File("C:\\path\\to\\ssh\\private\\key.pem"));
		
		// Using a password
		//config.setSshPassword("password123");
		
		// the resulting jdbc of the example: (NOTE: THE PORT = LOCAL PORT)
		String dbUrl = "jdbc:mysql://localhost:3366/" + "DATABASE_NAME_HERE" + "?useSSL=false";
		
		// Connect to server over SSH
		SSHTunnelHandler.createSSHTunnel(config);
		
		// Connect to DB and execute request
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(dbUrl, "DB_USERNAME_HERE", "DB_PASSWORD_HERE");
			statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT title FROM article");
			
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) { rs.close(); }
			if (statement != null) { statement.close(); }
			if (connection != null) { connection.close(); }
		}
		
		// Close SSH tunnel
		SSHTunnelHandler.closeSSHTunnel();
	}
	
}
