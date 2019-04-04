package managedBeans;

import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import org.aspgroup1.crud.DoctorCrud;
import org.aspgroup1.entity.Doctor;

/**
 *
 * @author Jonathan Anders
 */
@ManagedBean(name = "viewDoctorBean")
@RequestScoped
public class viewDoctorBean {
    
    private DoctorCrud dc;
    
    private List<Doctor> doctors;
    private int numDoc;
    
    //Grabs list of doctors from DataBase
    public viewDoctorBean() throws SQLException
    {
        dc = new DoctorCrud();
        doctors = dc.getDoctors();
        
        //doctors = DoctorCrud.getDoctors();  
        //numDoc = doctors.size();
    }
    
    //Getter for list
    public List<Doctor> getDoctors() {return doctors;}
    public int getNumDoc(){return numDoc;}

}
