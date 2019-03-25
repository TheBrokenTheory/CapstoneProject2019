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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Jonathan
 */
@ManagedBean(name = "manageUsersBean")
@RequestScoped
public class manageUsersBean implements Serializable {
    
    private static final Account[] accounts = new Account[12];
    //List<Account> accounts = new ArrayList<Account>();
    private String username = "";
    private String password = "";
    private int accountType = 0;
    private String firstName = "";
    private String lastName = "";
    

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
            int counter = 0;
            
            while (resultSet.next())
            {
                String un = resultSet.getString("username");
                String pass = resultSet.getString("password");
                String acctType = resultSet.getString("accountType");
                int acct = Integer.parseInt(acctType);
                String fName = resultSet.getString("fname");
                String lName = resultSet.getString("lname");
                accounts[counter] = new Account(un, pass, acct, fName, lName);
                System.out.println(accounts[counter].getUsername());
                System.out.println(accounts[counter].getPassword());
                System.out.println(accounts[counter].getAccountType());
                System.out.println(accounts[counter].getFirstName());
                System.out.println(accounts[counter].getLastName());
                
                //Account Type: 1 for admin, 2 for general user
                //TODO: add redirect to proper page based on acct Type
                String AccountType = resultSet.getString("accountType");
                String dbPassword = resultSet.getString("password");
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
    
    
}
