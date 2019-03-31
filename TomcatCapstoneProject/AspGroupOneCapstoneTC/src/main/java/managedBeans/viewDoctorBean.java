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
@RequestScoped
public class viewDoctorBean {
    
    private List<Doctor> doctors = DoctorCrud.getDoctors();
    private int numDoc;
    
    //Grabs list of doctors from DataBase
    public viewDoctorBean() throws SQLException
    {
        //doctors = DoctorCrud.getDoctors();  
        //numDoc = doctors.size();
    }
    
    //Getter for list
    public List<Doctor> getDoctors() {return doctors;}
    public int getNumDoc(){return numDoc;}

}
