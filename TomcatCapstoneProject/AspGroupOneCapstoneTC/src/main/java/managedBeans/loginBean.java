package managedBeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.aspgroup1.crud.UserCrud;
import org.aspgroup1.entity.User;


/**
 *
 * @author Jonathan Anders
 */
@ManagedBean(name = "loginBean")
@ViewScoped
public class loginBean implements Serializable {
    
    private String username = "";
    private String loginErrorMsg= "";
    private String password = "";
    private String page="";
    
    private UserCrud ud;
    private List<User> users;
    
    /**
     * Creates a new instance of loginBean
     */
    public loginBean()
    {
        ud = new UserCrud();
        users = ud.getUsers();
    }
    
    //Verify user credentials against DataBase
    public String login()
    {
        boolean accountMatch = false;
        
        for (int i = 0; i < users.size(); i++)
        {
            if(username.equals(users.get(i).getUsername()))
            {
                if(password.equals(users.get(i).getPassword()))
                {
                    if (users.get(i).getAccountType() == 1)
                    {
                       page = "index"; 
                    }
                    else if(users.get(i).getAccountType() == 2)
                    {
                        page = "genUserDashboard";
                    }
                    
                    accountMatch = true;
                    //Breaks out of for loop if it finds account match
                    break;
                }
                else
                {
                    loginErrorMsg = "Invalid Username or Password";
                }
            }
            else
            {
                loginErrorMsg = "Invalid Username or Password";
            } 
        }

        return page;
    }

    //Get and set for return page
    public String getPage() {return page;}
    public void setPage(String currentPage){this.page=currentPage;}

    //Getters and setters
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getLoginErrorMsg(){return loginErrorMsg;}
    public void setLoginErrorMsg(String errMsg) {this.loginErrorMsg = errMsg;}
}
