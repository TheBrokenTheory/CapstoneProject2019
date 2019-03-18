package managedBeans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jonathan Anders
 */
@ManagedBean(name = "loginBean")
@ViewScoped
public class loginBean implements Serializable {
    //Connection conn = null;
    //Statement readStatement = null;
    //ResultSet resultSet = null;
    //String readTable="SELECT * FROM Doctor;";
    //String results = "";
    private String username = "";
    private String password = "";
    
    
    /**
     * Creates a new instance of loginBean
     * @throws java.sql.SQLException
     */
    public loginBean() throws SQLException
    {
    }

    private static Connection getRemoteConnection() throws ClassNotFoundException, SQLException
    {
        Connection conn = null;
        try {
            //System.out.println("Loading driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("Driver loaded!");
          } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find the driver in the classpath!", e);
          }
        conn = DriverManager.getConnection("jdbc:mysql://aspgroup1@finalaspgroup1.chxan6yoffks.us-east-2.rds.amazonaws.com:3306/aspgroup1?user=aspgroup1&password=finalaspgroup1");
        return conn;
    }
    
    
    public void login() throws SQLException, ClassNotFoundException
    {
    
        Statement readStatement = null;
        ResultSet resultSet = null;
        String results = "";
        int numresults = 0;
        String statement = null;
        Connection con = getRemoteConnection();
        
        try {
            readStatement = con.createStatement();
            resultSet = readStatement.executeQuery("SELECT username FROM Users;");
            
            ResultSetMetaData metadata = resultSet.getMetaData();
            int numberOfColumns = metadata.getColumnCount();
            ArrayList<String> arrayList = new ArrayList<String>();
            boolean usernameMatch = false;
            
            while (resultSet.next() || usernameMatch==true)
            {
                results = resultSet.getString("username");
                if(username.equals(results))
                {
                    System.out.println("Match!");
                    usernameMatch = true;
                    resultSet.close();
                    readStatement.close();
                }
                else
                {
                    System.out.println("No Match");
                }
            }
            
            
            
            //resultSet.first();
            //results = resultSet.getString("username");
            //resultSet.next();
            //results += ", " + resultSet.getString("username");
            //System.out.println(results);
            //System.out.println(username + " " + password);
            resultSet.close();
            readStatement.close();
            con.close();

          } catch (SQLException ex) {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
          } finally {
                System.out.println("Closing the connection.");
                if (con != null) try { con.close(); } catch (SQLException ignore) {}
          }

    }
    
    
    //Getters and setters
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
