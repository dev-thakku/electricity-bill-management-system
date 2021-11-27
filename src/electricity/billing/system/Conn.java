
package electricity.billing.system;

import java.sql.*;

/**
 * @author souma (SMIT)
 * @version v1.0
 * 
 */
public class Conn {
    private Connection connection;
    public Statement statement;
    
    private final String DATABASE_NAME = "ebs";
    private final String CONNECTION_URL = "jdbc:mysql://localhost/"+DATABASE_NAME;
    private final String USER_NAME = "root";
    private final String PASSWORD = "";
    
    public Conn(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(CONNECTION_URL,USER_NAME,PASSWORD);
            statement = connection.createStatement();
            System.out.println("Successfully connected with "+DATABASE_NAME+" database...!!!");
        } catch (ClassNotFoundException | SQLException ex) {
           ex.printStackTrace();
        }
        
    }
    
}
