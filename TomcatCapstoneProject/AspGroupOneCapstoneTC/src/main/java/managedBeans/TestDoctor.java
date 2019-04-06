
package managedBeans;

/**
 *
 * @author Jonathan Anders
 */
public class TestDoctor {

    private String docFirstName = "";
    private String docLastName = "";
    private int docID = 0;
    private String docSpeciality = "";

    public TestDoctor(int id, String fname, String lname, String spec)
    {
        this.docID = id;
        this.docFirstName = fname;
        this.docLastName = lname;
        this.docSpeciality = spec;
    }
    
    public String getDocFirstName() {return docFirstName;}
    public void setDocFirstName(String name){this.docFirstName = name;}
    public String getDocLastName() {return docLastName;}
    public void setDocLastName(String name){this.docLastName = name;}
    public int getDocID() {return docID;}
    public void setDocFirstName(int id){this.docID = id;}
    public String getDocSpeciality() {return docSpeciality;}
    public void setDocSpeciality(String spec){this.docSpeciality = spec;}
    
    
}
