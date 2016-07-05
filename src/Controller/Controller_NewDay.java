package Controller;

import Database.DB_NewDayHandler;
import Model.Session;
import View.CurrentStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by roije on 17/06/2016.
 */
public class Controller_NewDay implements Initializable
{
    private DB_NewDayHandler newDayHandler = new DB_NewDayHandler();

    @FXML
    Button backBtn, backBtnLogo;

    @FXML
    ComboBox<String> fromBox, toBox;

    @FXML
    DatePicker datePicker;

    @FXML
    ImageView errorIcon;

    @FXML
    Label errorLabel;

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
        //If day doesn't already exists
        if (!newDayHandler.dayExists(Session.getCurrentJob().getJobId(), datePicker))
        {
            newDayHandler.saveDay(datePicker, fromBox, toBox);

            //Update the current job object
            Session.getCurrentJob().setTotalHours(newDayHandler.getTotalHoursForSession(Session.getCurrentJob().getJobId()));
            Session.getCurrentJob().setTotalPay(newDayHandler.getTotalPayForSession(Session.getCurrentJob().getJobId()));
            Session.getCurrentJob().setTotalDays(newDayHandler.getTotalDaysForSession(Session.getCurrentJob().getJobId()));
            changeToPreviousWindow();
        }
        else
        {
            errorIcon.setVisible(true);
            errorLabel.setText("Dagur er longu skrásettur");
            errorLabel.setVisible(true);
        }
    }

    public void clearErrors()
    {
        errorIcon.setVisible(false);
        errorLabel.setVisible(false);
    }

    public void changeToPreviousWindow()
    {
        Stage stage = new Stage();
        Parent root = null;
        try
        {
            root = FXMLLoader.load(getClass().getResource("/View/FXMLJobPage_Window.fxml"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);

        CurrentStage.getCurrentStage().close();
        CurrentStage.setCurrentStage(stage);
        CurrentStage.showCurrentStage();
    }


}
