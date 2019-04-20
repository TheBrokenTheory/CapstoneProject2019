
package org.aspgroup1.utilities;

/**
 *
 * @author Jonathan Anders
 */
public class UtilityMethods {
    
    public static int convertBool(boolean bool)
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

    //Converts time to proper format for fullCalendar
    public static String convertTime(String fullString)
    {
        String timeSub = fullString.substring(11, 16);
        return timeSub;
    }
    
    //Converts date to proper format for fullCalendar
    public static String convertDate(String fullString)
    {  
        //Substring locations
        //Mon 4-7 Day 8-10 Year 24 - 28
        
        //Gets substrings from the date/time input
        String strMonth = fullString.substring(4, 7);
        
        //Converts month from 3 lttr abbriviation to number
        String month = convertMonth(strMonth);
        String day = fullString.substring(8, 10);
        String year = fullString.substring(24, 28);
        
        //Date compatable with fullCal component
        String date = year + "-" + month + "-" + day;
        return date;
    }
    
    //Convert month from abbriviation to number
    public static String convertMonth(String abbrMonth)
    {
        String month = "";
        
        switch (abbrMonth)
        {
            case "Jan":
                month = "01";
                break;
            case "Feb":
                month = "02";
                break;
            case "Mar":
                month = "03";
                break;
            case "Apr":
                month = "04";
                break;
            case "May":
                month = "05";
                break;
            case "Jun":
                month = "06";
                break;
            case "Jul":
                month = "07";
                break;
            case "Aug":
                month = "08";
                break;
            case "Sep":
                month = "09";
                break;
            case "Oct":
                month = "10";
                break;
            case "Nov":
                month = "11";
                break;
            case "Dec":
                month = "12";
                break;
        }
        
        return month;
    }
}
