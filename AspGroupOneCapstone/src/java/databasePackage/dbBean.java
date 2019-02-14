package databasePackage;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Jonathan
 */
@ManagedBean (name = "dbBean")
@RequestScoped
public class dbBean implements Serializable 
{

    private String firstName;
    private String lastName;
    private int docId;
    /**
     * Creates a new instance of dbBean
     */
    public dbBean()
    {
    }
    
    //Getters and setters
    public String getFirstName() {return firstName;}
    public void setFirstName(String name) {this.firstName = name;}
    public String getLastName() {return lastName;}
    public void setLastName(String name) {this.lastName = name;}
    public int getDocId() {return docId;}
    public void setDocId(int id) {this.docId = id;}
    
    
}
