package Controller;

import Model.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by roije on 15/06/2016.
 */
public class Controller_FrontPage implements Initializable
{
    //Object is used to get the date, day and right greeting in several methods in this class
    private Calendar calendar = Calendar.getInstance();

    @FXML
    Label headerLabel;

    @FXML
    Label dayLabel;

    @FXML
    Label dateLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Date currentTime = calendar.getTime();

        int hour = checkTimeOfDay();
        if (hour >= 6 && hour <= 11)
        {
            headerLabel.setText("Góðan morgun " + Session.getCurrentUser().getFirstName());
        }
        else if(hour > 11 && hour <= 17)
        {
            headerLabel.setText("Góðan dagin " + Session.getCurrentUser().getFirstName());
        }
        else
            headerLabel.setText("Gott kvøld " + Session.getCurrentUser().getFirstName());

        //Set the name of the day
        dayLabel.setText(getDayName(currentTime));

        //Set the date
        dateLabel.setText(getDate(currentTime));

    }

    public int checkTimeOfDay()
    {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    public String getDayName(Date currentTime)
    {
        String dayName = currentTime.toString().substring(0,3);
        switch (dayName)
        {
            case "Mon": dayName = "mánadagur";
                break;
            case "Tue": dayName  = "týsdagur";
                break;
            case "Wed": dayName  = "mikudagur";
                break;
            case "Thu": dayName  = "hósdagur";
                break;
            case "Fri": dayName  = "fríggjadagur";
                break;
            case "Sat": dayName  = "leygardagur";
                break;
            case "Sun": dayName  = "sunnudagur";
                break;
        }
        return dayName;
    }

    public String getDate(Date currentTime)
    {
        //Get name of month
        String nameOfMonth = currentTime.toString().substring(4,7);

        //Translate into faroese
        switch (nameOfMonth)
        {
            case "Jan": nameOfMonth = "januar";
                break;
            case "Feb": nameOfMonth  = "februar";
                break;
            case "Mar": nameOfMonth  = "mars";
                break;
            case "Apr": nameOfMonth  = "apríl";
                break;
            case "May": nameOfMonth  = "mei";
                break;
            case "Jun": nameOfMonth  = "juni";
                break;
            case "Jul": nameOfMonth  = "juli";
                break;
            case "Aug": nameOfMonth  = "august";
                break;
            case "Sep": nameOfMonth  = "septembur";
                break;
            case "Oct": nameOfMonth  = "oktobur";
                break;
            case "Nov": nameOfMonth = "novembur";
                break;
            case "Dec": nameOfMonth  = "desembur";
                break;
        }

        System.out.println(nameOfMonth);

        String date = currentTime.toString().substring(8,9);

        String year = currentTime.toString().substring(25,29);
        System.out.println(year);

        return date + ". " + nameOfMonth + " " + year;
    }
}
