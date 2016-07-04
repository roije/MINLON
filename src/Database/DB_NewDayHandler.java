package Database;

import Model.Job;
import Model.Session;
import com.mysql.jdbc.Connection;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by roije on 17/06/2016.
 */
public class DB_NewDayHandler
{
    private Connection databaseConnector = DB_Connector.getConnection();

    public void saveDay(DatePicker datePicker, ComboBox start, ComboBox end)
    {
        String dayName = getDayName(datePicker);
        int startTime = timeToInteger(start);
        int endTime = timeToInteger(end);
        ArrayList<Integer> workHours = calcHours(startTime, endTime);
        double totalPay = calcPay(workHours, dayName);
        int totalHours = workHours.size();
        int regularHours = 0;
        int firstOverTimeHours = 0;
        int secondOverTimeHours = 0;
        int saturdayHours = 0;
        int sundayHours = 0;

        if (dayName.equals("Leygardagur"))
        {
            saturdayHours = totalHours;
        }
        else if (dayName.equals("Sunnudagur"))
        {
            sundayHours = totalHours;
        }
        else
        {
            int[] hourDist = getHourDistribution(workHours);
            regularHours = hourDist[0];
            firstOverTimeHours = hourDist[1];
            secondOverTimeHours = hourDist[2];
        }

        /*
        //A lot of stuff to find date and name of day and translate it to Faroese
        LocalDate dateFromPicker = datePicker.getValue();
        //System.out.print(date);
        String day = dateFromPicker.toString().substring(8,10);
        String month = dateFromPicker.toString().substring(5,7);
        String year = dateFromPicker.toString().substring(0,4);
        */
        String date = formatDate(datePicker);


        String sql = "INSERT INTO Days (dayName, dayDate, totalHours, hoursReg, firstOver, secondOver, saturday, sunday, " +
                "totalPay, jobId)" +
                "VALUES (?, ?, ?, ?, ? ,?, ?, ? , ?, ?)";

        try
        {
            PreparedStatement preparedStatement = databaseConnector.prepareStatement(sql);
            preparedStatement.setString(1, dayName);
            preparedStatement.setString(2, date);
            preparedStatement.setInt(3, totalHours);
            preparedStatement.setInt(4, regularHours);
            preparedStatement.setInt(5, firstOverTimeHours);
            preparedStatement.setInt(6, secondOverTimeHours);
            preparedStatement.setInt(7, saturdayHours);
            preparedStatement.setInt(8, sundayHours);
            preparedStatement.setDouble(9, totalPay);
            preparedStatement.setInt(10, Session.getCurrentJob().getJobId());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public String getDayName(DatePicker datePicker)
    {
        //A lot of stuff to find date and name of day and translate it to Faroese
        LocalDate dateFromPicker = datePicker.getValue();
        Instant instant = Instant.from(dateFromPicker.atStartOfDay(ZoneId.systemDefault()));
        Date fullDate = Date.from(instant);
        String dateToString = fullDate.toString();
        String getDay = dateToString.substring(0,3);
        String dayToFaroese = null;
        switch (getDay)
        {
            case "Mon": dayToFaroese = "Mánadagur";
                break;
            case "Tue": dayToFaroese = "Týsdagur";
                break;
            case "Wed": dayToFaroese = "Mikudagur";
                break;
            case "Thu": dayToFaroese = "Hósdagur";
                break;
            case "Fri": dayToFaroese = "Fríggjadagur";
                break;
            case "Sat": dayToFaroese = "Leygardagur";
                break;
            case "Sun": dayToFaroese = "Sunnudagur";
                break;
        }
        return dayToFaroese;
    }

    public String formatDate(DatePicker datePicker)
    {
        String date; //Value to return

        //A lot of stuff to find date and name of day and translate it to Faroese
        LocalDate dateFromPicker = datePicker.getValue();
        //System.out.print(date);
        String day = dateFromPicker.toString().substring(8,10);
        String month = dateFromPicker.toString().substring(5,7);
        String year = dateFromPicker.toString().substring(0,4);

        date = day + "-" + month + "-" + year;

        return date;
    }

    public int timeToInteger(ComboBox time)
    {
        int timeInt;
        //Get value out of combobox into a string. Make a substring to remove starting 0, this is for calculating.
        String timeStr = time.getValue().toString();
        if(timeStr.startsWith("0"))
        {
            timeStr = timeStr.substring(1,2);
            timeInt = Integer.parseInt(timeStr);
        }
        else
        {
            timeStr = timeStr.substring(0,2);
            timeInt = Integer.parseInt(timeStr);
        }
        return timeInt;
    }

    public ArrayList<Integer> calcHours(int start, int end)
    {

        ArrayList<Integer> workHours = new ArrayList<>();

        if (start == end)
        {
            for (int i = 1; i <= 24; i++)
            {
                workHours.add(i);
            }
            System.out.println(workHours.size());
            return workHours;
        }

        if (start > end)
        {
            for (int j = start; j!= end; j++)
            {
                if (j <= 24)
                {
                    workHours.add(j);
                }
                else
                {
                    j = 0;
                }

            }
        }
        else
        {
            for (int i = start + 1; i <= end; i++)
            {
                workHours.add(i);
            }
        }

        return workHours;
    }

    public double calcPay(ArrayList<Integer> workHours, String dayName)
    {
        double regJobPay = Session.getCurrentJob().getRegularPay();
        double firstOverJobPay = Session.getCurrentJob().getFirstOverPay();
        double secondOverJobPay = Session.getCurrentJob().getSecondOverPay();
        double satJobPayment = Session.getCurrentJob().getSaturdayPay();
        double sunJobPayment = Session.getCurrentJob().getSundayPay();

        double totalPayment = 0;

        if (dayName.equals("Leygardagur")) {
            for (Integer hour : workHours)
            {
                totalPayment += satJobPayment;
            }
        }
        else if (dayName.equals("Sunnudagur"))
        {
            for (Integer hour : workHours)
            {
                totalPayment += sunJobPayment;
            }
        }
        else
        {
            for (Integer hour : workHours)
            {
                if (hour >= 8 && hour <= 16)
                {
                    totalPayment += regJobPay;
                } else if (hour >= 17 && hour <= 19)
                {
                    totalPayment += firstOverJobPay;
                } else
                {
                    totalPayment += secondOverJobPay;
                }
            }
        }

        return totalPayment;
    }

    public int[] getHourDistribution(ArrayList<Integer> workHours)
    {
        int reg = 0;
        int firstOver = 0;
        int secondOver = 0;
        
        for (Integer hour : workHours)
        {
            if (hour > 8 && hour <= 16)
            {
                reg++;
            }
            else if (hour > 16 && hour <= 20)
            {
                firstOver++;
            }
            else
            {
                secondOver++;
            }
        }
        int[] hours = {reg, firstOver, secondOver};
        return hours;
    }


    public boolean dayExists(int jobId, DatePicker datePicker)
    {
        boolean exists = false;
        ResultSet resultSet;
        String date = formatDate(datePicker);
        String sqlQuery = "SELECT * FROM Days WHERE jobId = '"+jobId+"' AND dayDate = '"+date+"'";
        try
        {
            resultSet = databaseConnector.createStatement().executeQuery(sqlQuery);
            if (resultSet.next())
            {
                exists = true;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return exists;
    }

}
