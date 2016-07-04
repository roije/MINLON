package Controller;

import Database.DB_NewDayHandler;
import Model.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by roije on 17/06/2016.
 */
public class Controller_NewDay implements Initializable
{
    private DB_NewDayHandler newDayHandler = new DB_NewDayHandler();

    @FXML
    ComboBox<String> fromBox, toBox;

    @FXML
    DatePicker datePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //Add clock hours to comboboxes
        fromBox.getItems().addAll("07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00",
                "16:00", "17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00","01:00",
                "02:00","03:00","04:00","05:00","06:00");

        toBox.getItems().addAll("16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00",
                "24:00", "01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00",
                "11:00","12:00","13:00","14:00","15:00");
    }

    public void saveBtnAction()
    {
        if (!newDayHandler.dayExists(Session.getCurrentJob().getJobId(), datePicker))
        {
            newDayHandler.saveDay(datePicker, fromBox, toBox);
        }
        else
        {
            System.out.println("Day already saved");
        }
    }
}
