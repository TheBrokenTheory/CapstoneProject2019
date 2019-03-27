package managedBeans;


import javax.enterprise.context.RequestScoped;
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

@ManagedBean(name = "manageUsersBean")
@RequestScoped
public class manageUsersBean implements Serializable {
    
    List<Account> accounts = new ArrayList<Account>();
    private String username = "";
    private String password = "";
    private int accountType = 0;
    private String firstName = "";
    private String lastName = "";
    private String acctTypeString = "";
    private String page="manageUsers";

    /**
     * Creates a new instance of manageUsersBean
     */
    public manageUsersBean() throws SQLException 
    {
        try {
            //Gets all users from DB at creation
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
    
    //Reads form the database and pulls all users into a list
    public List getExistingAccounts() throws ClassNotFoundException, SQLException
    {
        Statement readStatement = null;
        ResultSet resultSet = null;
        Connection con = getRemoteConnection();
        accounts.clear();

        try {
            readStatement = con.createStatement();
            resultSet = readStatement.executeQuery("SELECT * FROM Users;");
            
            while (resultSet.next())
            {
                //Account atributes
                String un = resultSet.getString("username");
                String pass = resultSet.getString("password");
                String acctType = resultSet.getString("accountType");
                int acct = Integer.parseInt(acctType);
                String fName = resultSet.getString("fname");
                String lName = resultSet.getString("lname");
                
                //Create new account class and add to the list
                accounts.add(new Account(un, pass, acct, fName, lName));
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
        //Convert from string to int
        accountType = Integer.parseInt(acctTypeString);
        Statement writeStatement = null;

        Connection con = getRemoteConnection();
        
        //Insert new user into DB
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
        
        //update table
        getExistingAccounts();
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
    public List getAccounts() {return accounts;}
    public String getAccountTypeString(){return acctTypeString;}
    public void setAccountTypeString(String type) {this.acctTypeString = type;}
    public String getPage() {return page;}
    public void setPage(String currentPage){this.page=currentPage;}
    
}