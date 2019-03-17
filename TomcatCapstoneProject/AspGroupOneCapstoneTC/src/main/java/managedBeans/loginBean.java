package managedBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Jonathan Anders
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class loginBean {
    
    //Connection conn = null;
    //Statement readStatement = null;
    //ResultSet resultSet = null;
    //String readTable="SELECT * FROM Doctor;";
    //String results = "";
    String name="test";
    String nametwo="test2";
    /**
     * Creates a new instance of loginBean
     * @throws java.sql.SQLException
     */
    public loginBean() throws SQLException
    {
        System.out.println("test");
    }

    private static Connection getRemoteConnection() throws ClassNotFoundException, SQLException {
        return null;
        /*
        System.out.println("testingfuck");
        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbName = System.getProperty("aspgroupwebproj");
        String userName = System.getProperty("aspgroup1");
        String password = System.getProperty("finalaspgroup1");
        String hostname = System.getProperty("aspgroupwebproj.chxan6yoffks.us-east-2.rds.amazonaws.com");
        String port = System.getProperty("3303");
        String jdbcUrl = "jdbc:mysql://" + hostname + ":3303" + "/" + dbName + "?user=" + userName + "&password=" + password;
        Connection con = DriverManager.getConnection(jdbcUrl);
        return con;
        */
  }
    
    public void login() throws SQLException, ClassNotFoundException
    {
         // Read RDS connection information from the environment
  String dbName = System.getProperty("aspgroup1");
  String userName = System.getProperty("aspgroup1");
  String password = System.getProperty("finalaspgroup1");
  String hostname = System.getProperty("aspgroup1@finalaspgroup1.chxan6yoffks.us-east-2.rds.amazonaws.com");
  String port = System.getProperty("3306");
  String jdbcUrl = "jdbc:mysql://" + hostname + ":3306" + "/" + dbName + "?user=" + userName + "&password=" + password;
  
  // Load the JDBC driver
  try {
    System.out.println("Loading driver...");
    Class.forName("com.mysql.cj.jdbc.Driver");
    System.out.println("Driver loaded!");
  } catch (ClassNotFoundException e) {
    throw new RuntimeException("Cannot find the driver in the classpath!", e);
  }

  Connection conn = null;
  Statement setupStatement = null;
  Statement readStatement = null;
  ResultSet resultSet = null;
  String results = "";
  int numresults = 0;
  String statement = null;

  try {
    // Create connection to RDS DB instance
    conn = DriverManager.getConnection("jdbc:mysql://aspgroup1@finalaspgroup1.chxan6yoffks.us-east-2.rds.amazonaws.com:3306/aspgroup1?user=aspgroup1&password=finalaspgroup1");
    
    // Create a table and write two rows
    setupStatement = conn.createStatement();
    String createTable = "CREATE TABLE TestingTwo (Resource char(50));";
    String insertRow1 = "INSERT INTO TestingTwo (Resource) VALUES ('EC2 Instance');";
    String insertRow2 = "INSERT INTO TestingTwo (Resource) VALUES ('RDS Instance');";
    
    setupStatement.addBatch(createTable);
    setupStatement.addBatch(insertRow1);
    setupStatement.addBatch(insertRow2);
    setupStatement.executeBatch();
    setupStatement.close();
    
  } catch (SQLException ex) {
    // Handle any errors
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
  } finally {
    System.out.println("Closing the connection.");
    if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
  }

  try {
    conn = DriverManager.getConnection(jdbcUrl);
    
    readStatement = conn.createStatement();
    resultSet = readStatement.executeQuery("SELECT Resource FROM Beanstalk;");

    resultSet.first();
    results = resultSet.getString("Resource");
    resultSet.next();
    results += ", " + resultSet.getString("Resource");
    
    resultSet.close();
    readStatement.close();
    conn.close();

  } catch (SQLException ex) {
    // Handle any errors
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
  } finally {
       System.out.println("Closing the connection.");
      if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
  }
    }
    public String getName() {return name;}
    public String getNameTwo() {return nametwo;}
    public void setName(String name) {this.name = name;}
    public void setNameTwo(String name) {this.nametwo = name;}
}
