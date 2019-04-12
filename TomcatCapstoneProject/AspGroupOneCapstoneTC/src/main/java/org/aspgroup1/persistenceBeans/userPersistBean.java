package org.aspgroup1.persistenceBeans;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.aspgroup1.crud.UserCrud;
import org.aspgroup1.entity.User;


@ManagedBean(name = "userBean")
@ViewScoped
public class userPersistBean implements Serializable {
    
    private List<User> usersL;
    User userObj;
    UserCrud uc;
    private String userUN;
    private String userPass;
    private int userAcctType;
    private String userFN;
    private String userLN;
    private String page="";
    private String loginErrorMsg= "";
    private String acctTypeString = "";
    
    public userPersistBean()
    {
        uc = new UserCrud();
        usersL = new ArrayList(uc.getUsers());
    }
    
    public void createUser()
    {
        userAcctType = Integer.parseInt(acctTypeString);
        uc.createUser(this.userUN, this.userPass, this.userAcctType, this.userFN, this.userLN);
        
        //Update list
        getUsersL();
    }
    
    public List getUsersL()
    {
        usersL.clear();
        usersL = new ArrayList(uc.getUsers());
        
        return usersL;
    }
    
    public User getUser()
    {
        userObj = uc.findByUserName(this.userUN);
        return userObj;
    }
    
    public void deleteUser()
    {
        uc.deleteUser(this.userUN);
    }
    
    //Verify user credentials against DataBase
    public String login()
    {
        boolean accountMatch = false;
        
        for (int i = 0; i < usersL.size(); i++)
        {
            if(userUN.equals(usersL.get(i).getUsername()))
            {
                if(userPass.equals(usersL.get(i).getPassword()))
                {
                    if (usersL.get(i).getAccountType() == 1)
                    {
                       page = "index"; 
                    }
                    else if(usersL.get(i).getAccountType() == 2)
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
    
    
    //Getters and Setters
    public String getUserUN(){return this.userUN;}
    public void setUserUN(String userName) {this.userUN = userName;}
    public String getUserPass() {return this.userPass;}
    public void setUserPass(String pass) {this.userPass = pass;}
    public int getUserAcctType() {return this.userAcctType;}
    public void setUserAcctType(int type) {this.userAcctType = type;} 
    public String getUserFN(){return this.userFN;}
    public void setUserFN(String name) {this.userFN = name;}
    public String getUserLN() {return this.userLN;}
    public void setUserLN(String name) {this.userLN = name;}
    public String getPage() {return page;}
    public void setPage(String currentPage){this.page=currentPage;}
    public String getLoginErrorMsg(){return loginErrorMsg;}
    public void setLoginErrorMsg(String errMsg) {this.loginErrorMsg = errMsg;}
    public String getAccountTypeString(){return acctTypeString;}
    public void setAccountTypeString(String type) {this.acctTypeString = type;}
    
}
