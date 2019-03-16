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
    
    Connection conn = null;
    Statement readStatement = null;
    ResultSet resultSet = null;
    String readTable="SELECT * FROM Doctor;";
    String results = "";
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

        System.out.println("testingfuck");
        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbName = System.getProperty("aspgroupwebproj");
        String userName = System.getProperty("aspgroup1");
        String password = System.getProperty("aspgroup1");
        String hostname = System.getProperty("aspgroupwebproj.chxan6yoffks.us-east-2.rds.amazonaws.com");
        String port = System.getProperty("3303");
        String jdbcUrl = "jdbc:mysql://" + hostname + ":3303" + "/" + dbName + "?user=" + userName + "&password=" + password;
        Connection con = DriverManager.getConnection(jdbcUrl);
        return con;

  }
    
    public void login() throws SQLException, ClassNotFoundException
    {
        System.out.println("testTwo");
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Test3");
        String dbName = System.getProperty("aa1uoe8uwsl9q6i");
        String userName = System.getProperty("aspgroup1");
        String password = System.getProperty("aspgroup1");
        String hostname = System.getProperty("aa1uoe8uwsl9q6i.chxan6yoffks.us-east-2.rds.amazonaws.com");
        String port = System.getProperty("3303");
        String jdbcUrl = "jdbc:mysql://" + hostname + ":3303" + "/" + dbName + "?user=" + userName + "&password=" + password;
        System.out.println("Test44");
        Connection con = DriverManager.getConnection(jdbcUrl);
        
        System.out.println("test4");
        readStatement = con.createStatement();
        System.out.println("test5");
        resultSet = readStatement.executeQuery(readTable);
        System.out.println("test6");
        resultSet.first();
        System.out.println("test7");
        results = resultSet.getString(readTable);
        System.out.println("test8");
        System.out.println(results);
        System.out.println("test9");
        
        
        resultSet.close();
        readStatement.close();
        con.close();
    }
    public String getName() {return name;}
    public String getNameTwo() {return nametwo;}
    public void setName(String name) {this.name = name;}
    public void setNameTwo(String name) {this.nametwo = name;}
}
