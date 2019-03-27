package managedBeans;

/**
 *
 * @author Jonathan
 */
public class Account {
    private String username = "";
    private String password = "";
    private int accountType = 0;
    private String firstName = "";
    private String lastName = "";
    
    public Account(String un, String pass, int acctType, String fName, String lName)
    {
        this.username = un;
        this.password = pass;
        this.accountType = acctType;
        this.firstName = fName;
        this.lastName = lName;
    }
    
    //Getters and setters
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}  
    public String getFirstName(){return firstName;}
    public void setFirstName(String name) {this.firstName = name;}
    public String getLastName(){return lastName;}
    public void setLastName(String name) {this.lastName = name;}
    public int getAccountType(){return accountType;}
    public void setAccountType(int type) {this.accountType = type;}
}
