package managedBeans;

import java.sql.Date;
import java.sql.SQLException;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import org.aspgroup1.crud.DoctorCrud;
import org.aspgroup1.entity.Doctor;

/**
 *
 * @author Jeremy Black
 */
@ManagedBean
@Named(value = "registerDoctorBean")
@RequestScoped
public class registerDoctorBean 
{
    private String firstName;
    private String lastName;
    private String specialty;
    private Date dateOfBirth;
    private String phoneNumber;
    
    public registerDoctorBean() throws SQLException
    {
        
    }
    
    public void registerDoctor()
    {
        //Create new doctor
        DoctorCrud doc = new DoctorCrud();
        doc.createDoctor(firstName,lastName,specialty,dateOfBirth,phoneNumber);
    }
    
    //Getters and Setters
    public String getFirstName(){return firstName;}
    public void setFirstName(String fName){this.firstName = fName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lName){this.lastName = lName;}
    public String getSpecialty() {return specialty;}
    public void setSpecialty(String specialty){this.specialty = specialty;}
    public Date getDOB() {return dateOfBirth;}
    public void setDOB(Date dateOfBirth){this.dateOfBirth = dateOfBirth;}
    public String getPhoneNum() {return phoneNumber;}
    public void setPhoneNum(String phoneNumber){this.phoneNumber = phoneNumber;}
}