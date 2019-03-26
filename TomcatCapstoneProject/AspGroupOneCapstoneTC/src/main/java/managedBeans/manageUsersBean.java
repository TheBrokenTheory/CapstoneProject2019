package managedBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Jonathan
 */
@ManagedBean(name = "manageUsersBean")
@RequestScoped
public class manageUsersBean implements Serializable {
    
    private static final Account[] accounts = new Account[100];
    //List<Account> accounts = new ArrayList<Account>();
    private String username = "";
    private String password = "";
    private int accountType = 0;
    private String firstName = "";
    private String lastName = "";
    private String acctTypeString = "";
    int counter = 0;
    private String page="manageUsers";

    /**
     * Creates a new instance of manageUsersBean
     */
    public manageUsersBean() throws SQLException 
    {
        try {
            getExistingAccounts();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(manageUsersBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Gets connection to the database
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
    
    public Account[] getExistingAccounts() throws ClassNotFoundException, SQLException
    {
        Statement readStatement = null;
        ResultSet resultSet = null;
        String results = "";
        int numresults = 0;
        String statement = null;
        Connection con = getRemoteConnection();
        
        
        try {
            readStatement = con.createStatement();
            resultSet = readStatement.executeQuery("SELECT * FROM Users;");
            
            ResultSetMetaData metadata = resultSet.getMetaData();
            int numberOfColumns = metadata.getColumnCount();
            ArrayList<String> arrayList = new ArrayList<String>();
            boolean accountMatch = false;
            
            
            while (resultSet.next())
            {
                String un = resultSet.getString("username");
                String pass = resultSet.getString("password");
                String acctType = resultSet.getString("accountType");
                int acct = Integer.parseInt(acctType);
                String fName = resultSet.getString("fname");
                String lName = resultSet.getString("lname");
                accounts[counter] = new Account(un, pass, acct, fName, lName);

                counter++;
            }
            
            //Close DB Connection
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
        
        return accounts;
    }
    
    public String createNewUser() throws SQLException, ClassNotFoundException
    {
        accountType = Integer.parseInt(acctTypeString);
        Statement writeStatement = null;
        String results = "";
        int numresults = 0;
        String statement = null;
        
        Connection con = getRemoteConnection();
        
        String insertStringStmt = "INSERT INTO Users (username, password, accountType, fname, lname)"
                + "VALUES ('" + username + "', '" + password + "', " + accountType + ", '"
                + firstName + "', '" + lastName + "')";
        
        try {
            writeStatement = con.createStatement();
            writeStatement.executeUpdate(insertStringStmt);
            
            //Close DB Connection
            writeStatement.close();
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
        return page;
    }
    
    
    //Getters and setters
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}  
    public String getFirstName(){return firstName;}
    public void setFirstName(String name) {this.firstName = name;}
    public String getLastName(){return lastName;}
    public void setLastName(String name) {this.lastName = name;}
    public int getAccountType(){return accountType;}
    public void setAccountType(int type) {this.accountType = type;}
    public Account[] getAccounts() {return accounts;}
    public String getAccountTypeString(){return acctTypeString;}
    public void setAccountTypeString(String type) {this.acctTypeString = type;}
    public String getPage() {return page;}
    public void setPage(String currentPage){this.page=currentPage;}
    
}
