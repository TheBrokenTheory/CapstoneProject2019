package managedBeans;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import org.aspgroup1.crud.DoctorCrud;
import org.aspgroup1.crud.UserCrud;
import org.aspgroup1.entity.Doctor;
import org.aspgroup1.entity.User;

/**
 *
 * @author Jeremy Black
 */
@ManagedBean
@Named(value = "registerPatientBean")
@RequestScoped
public class registerPatientBean 
{
    private String firstName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private String phoneNumber;
    
    public registerPatientBean() throws SQLException
    {
        
    }
    
    //Getters and Setters
    public String getFirstName(){return firstName;}
    public void setFirstName(String fName){this.firstName = fName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lName){this.lastName = lName;}
    public String getGender() {return gender;}
    public void setGender(String gender){this.gender = gender;}
    public Date getDOB() {return dateOfBirth;}
    public void setDOB(Date dateOfBirth){this.dateOfBirth = dateOfBirth;}
    public String getPhoneNum() {return phoneNumber;}
    public void setPhoneNum(String phoneNumber){this.phoneNumber = phoneNumber;}
}