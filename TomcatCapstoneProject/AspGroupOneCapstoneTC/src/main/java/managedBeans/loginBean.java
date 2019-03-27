package managedBeans;

import java.io.IOException;
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
    
    private String username = "";
    private String loginErrorMsg= "";
    private String password = "";
    private static final long serialVersionUID = 1L;
    private String page="";
    
    /**
     * Creates a new instance of loginBean
     * @throws java.sql.SQLException
     */
    public loginBean(){}
    
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
    
    //Checks the credentials of the login form and either returns error message
    //or redirects to proper page based on account type
    public String login() throws SQLException, ClassNotFoundException, IOException
    {
    
        Statement readStatement = null;
        ResultSet resultSet = null;
        String results = "";
        
        Connection con = getRemoteConnection();
        
        try {
            readStatement = con.createStatement();
            resultSet = readStatement.executeQuery("SELECT * FROM Users;");
            
            ResultSetMetaData metadata = resultSet.getMetaData();
            boolean accountMatch = false;
            
            while (resultSet.next() || accountMatch==true)
            {
                results = resultSet.getString("username");
                
                //Account Type: 1 for admin, 2 for general user
                String accountTypeString = resultSet.getString("accountType");
                int accountType = Integer.parseInt(accountTypeString);
                
                String dbPassword = resultSet.getString("password");
                
                if(username.equals(results))
                {
                    if(dbPassword.equals(password))
                    {
                        if (accountType == 1)
                        {
                           page = "index"; 
                        }
                        else if(accountType == 2)
                        {
                            page = "genUserDashboard";
                        }
                        accountMatch = true;
                    }
                    else
                    {
                        loginErrorMsg = "Invalid Username or Password";
                    }
                }
                else
                {
                    loginErrorMsg = "Invalid Username or Password";
                }
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
        
        //Return page to be be displayed base on account type
        return page;
    }

    //Get and set for return page
    public String getPage() {return page;}
    public void setPage(String currentPage){this.page=currentPage;}

    //Getters and setters
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getLoginErrorMsg(){return loginErrorMsg;}
    public void setLoginErrorMsg(String errMsg) {this.loginErrorMsg = errMsg;}
}
