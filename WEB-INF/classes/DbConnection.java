package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection 
{
	 	private static DbConnection instance = null;
	    private Connection connection=null;
	    private final String jdbcurl="jdbc:postgresql://localhost:5432/shopping_system";
		private final String psqlUserName="postgres";
		private final String psqlPassword="admin";

	    private DbConnection() throws SQLException 
	    {
	    
	        connection = DriverManager.getConnection(jdbcurl, psqlUserName, psqlPassword);
	    
	    }

	    public static DbConnection getInstance() throws SQLException 
	    {
	    
	        if (instance == null) {
	            instance = new DbConnection();
	        }
	    
	        return instance;
	    
	    }

	    public Connection getConnection() throws SQLException {
	    	if(!connection.isValid(5)) {
	    		throw new SQLException();
	    	}
	        return connection;
	    }
	    public void closeConnection() throws SQLException {
	    	connection.close();
	    	connection=null;
	    }

}