package managedBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import org.aspgroup1.crud.DoctorCrud;
import org.aspgroup1.entity.Doctor;

/**
 *
 * @author Jonathan Anders
 */
@ManagedBean(name = "viewDoctorBean")
@ViewScoped
public class viewDoctorBean {
    
    private String docFirstName = "";
    private String docLastName = "";
    private int docID = 0;
    private String docSpeciality = "";
    private List<TestDoctor> doctors = new ArrayList<>();

    public viewDoctorBean() throws SQLException
    {
        try {
            //Gets all users from DB at creation
            getExistingAccounts();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(manageUsersBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getDocs() throws SQLException
    {

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
        doctors.clear();

        try {
            readStatement = con.createStatement();
            resultSet = readStatement.executeQuery("SELECT * FROM Doctor;");
            
            while (resultSet.next())
            {
                //Account atributes
                String docIDString = resultSet.getString("doctorId");
                int docID = Integer.parseInt(docIDString);
                String fName = resultSet.getString("doctorFirstName");
                String lName = resultSet.getString("doctorLastName");
                String ds = resultSet.getString("doctorSpecialty");
                
                //Create new account class and add to the list
                doctors.add(new TestDoctor(docID, fName, lName, ds));
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
        
        return doctors;
    }
    
    
    public List<TestDoctor> getDoctors() {return doctors;}
    public String getDocFirstName() {return docFirstName;}
    public void setDocFirstName(String name){this.docFirstName = name;}
    public String getDocLastName() {return docLastName;}
    public void setDocLastName(String name){this.docLastName = name;}
    public int getDocID() {return docID;}
    public void setDocFirstName(int id){this.docID = id;}
    public String getDocSpeciality() {return docSpeciality;}
    public void setDocSpeciality(String spec){this.docSpeciality = spec;}
}
