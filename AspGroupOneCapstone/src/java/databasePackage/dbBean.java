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
    /*
    I reccomend using a list, or array for DB quesry results
    Change code here depending on what you choose.
    This example might help:
    https://showcase.bootsfaces.net/forms/DataTable.jsf;jsessionid=MVCkGvoidu4GTGDZq7ZAP0EgLbOMTkpFHQR_kl5D.showcase04#unified_ajax_and_javascript_api
    
    Add any needed POJO to "databasePackage"
    */
    
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
