
package org.aspgroup1.persistenceBeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.aspgroup1.crud.DoctorCrud;
import org.aspgroup1.crud.ScheduleCrud;
import org.aspgroup1.entity.Doctor;
import org.aspgroup1.entity.Schedule;
import org.aspgroup1.utilities.UtilityMethods;

/**
 *
 * @author Jonathan Anders
 */
@ManagedBean(name = "scheduleBean")
@RequestScoped
public class schedulePersistBean implements Serializable{
    Schedule scheduleobj;
    ScheduleCrud sc;
    
    private boolean mondayBool;
    private boolean tuesdayBool;
    private boolean wednesdayBool;
    private boolean thursdayBool;
    private boolean fridayBool;
    private boolean saturdayBool;
    private boolean sundayBool;
    
    private long doctorIDB;
    private int monday;
    private int tuesday;
    private int wednesday;
    private int thursday;
    private int friday;
    private int saturday;
    private int sunday;
    
    public schedulePersistBean()
    {
     sc = new ScheduleCrud();
    }
    
    public void createSchedule(long docID)
    {
        
        System.out.println(docID);
        
    }
    
    public int convertBool(boolean bool)
    {
        int convertedBool = 0;
        
        if(bool == true)
        {
            convertedBool = 1;
        }
        else if (bool == false)
        {
            convertedBool = 0;
        }
        
        return convertedBool;
    }
    
    
    
    

    public long getDoctorIDB()
    {
        return doctorIDB;
    }

    public void setDoctorIDB(long doctorIDB)
    {
        this.doctorIDB = doctorIDB;
    }

    public int getMonday()
    {
        return monday;
    }

    public void setMonday(int monday)
    {
        this.monday = monday;
    }

    public int getTuesday()
    {
        return tuesday;
    }

    public void setTuesday(int tuesday)
    {
        this.tuesday = tuesday;
    }

    public int getWednesday()
    {
        return wednesday;
    }

    public void setWednesday(int wednesday)
    {
        this.wednesday = wednesday;
    }

    public int getThursday()
    {
        return thursday;
    }

    public void setThursday(int thursday)
    {
        this.thursday = thursday;
    }

    public int getFriday()
    {
        return friday;
    }

    public void setFriday(int friday)
    {
        this.friday = friday;
    }

    public int getSaturday()
    {
        return saturday;
    }

    public void setSaturday(int saturday)
    {
        this.saturday = saturday;
    }

    public int getSunday()
    {
        return sunday;
    }

    public void setSunday(int sunday)
    {
        this.sunday = sunday;
    }

    public Schedule getScheduleobj()
    {
        return scheduleobj;
    }

    public void setScheduleobj(Schedule scheduleobj)
    {
        this.scheduleobj = scheduleobj;
    }

    public ScheduleCrud getSc()
    {
        return sc;
    }

    public void setSc(ScheduleCrud sc)
    {
        this.sc = sc;
    }

    public boolean isMondayBool()
    {
        return mondayBool;
    }

    public void setMondayBool(boolean mondayBool)
    {
        this.mondayBool = mondayBool;
    }

    public boolean isTuesdayBool()
    {
        return tuesdayBool;
    }

    public void setTuesdayBool(boolean tuesdayBool)
    {
        this.tuesdayBool = tuesdayBool;
    }

    public boolean isWednesdayBool()
    {
        return wednesdayBool;
    }

    public void setWednesdayBool(boolean wednesdayBool)
    {
        this.wednesdayBool = wednesdayBool;
    }

    public boolean isThursdayBool()
    {
        return thursdayBool;
    }

    public void setThursdayBool(boolean thursdayBool)
    {
        this.thursdayBool = thursdayBool;
    }

    public boolean isFridayBool()
    {
        return fridayBool;
    }

    public void setFridayBool(boolean fridayBool)
    {
        this.fridayBool = fridayBool;
    }

    public boolean isSaturdayBool()
    {
        return saturdayBool;
    }

    public void setSaturdayBool(boolean saturdayBool)
    {
        this.saturdayBool = saturdayBool;
    }

    public boolean isSundayBool()
    {
        return sundayBool;
    }

    public void setSundayBool(boolean sundayBool)
    {
        this.sundayBool = sundayBool;
    }
 

}
