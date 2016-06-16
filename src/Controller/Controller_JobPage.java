package Controller;

import Model.Session;
import View.CurrentStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by roije on 16/06/2016.
 */
public class Controller_JobPage implements Initializable
{
    @FXML
    Button backBtnLogo, backBtnText, homeBtnLogo, homeBtnText;

    @FXML
    Label regularLabel, firstOverLabel, secondOverLabel, saturdayLabel, sundayLabel, jobNameLabel;

    @FXML
    Label totalHoursLabel, totalDaysLabel, beforeTaxLabel, afterTaxLabel, averageLabel, holidayLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        jobNameLabel.setText(Session.getCurrentJob().getJobName());
        regularLabel.setText(String.valueOf(Session.getCurrentJob().getRegularPay()));
        firstOverLabel.setText(String.valueOf(Session.getCurrentJob().getFirstOverPay()));
        secondOverLabel.setText(String.valueOf(Session.getCurrentJob().getSecondOverPay()));
        saturdayLabel.setText(String.valueOf(Session.getCurrentJob().getSaturdayPay()));
        sundayLabel.setText(String.valueOf(Session.getCurrentJob().getSundayPay()));
        totalHoursLabel.setText(String.valueOf(Session.getCurrentJob().getTotalHours()));
        totalDaysLabel.setText(String.valueOf(Session.getCurrentJob().getTotalDays()));
        beforeTaxLabel.setText(String.valueOf(Session.getCurrentJob().getTotalPay()));
        afterTaxLabel.setText(String.valueOf(calcTaxPay(Session.getCurrentJob().getTotalPay())));
        averageLabel.setText(String.valueOf(calcAveragePay(Session.getCurrentJob().getTotalDays(),
                Session.getCurrentJob().getTotalPay())));
        holidayLabel.setText(String.valueOf(calcHoliday(Session.getCurrentJob().getTotalPay())));
    }

    public double calcTaxPay(double totalPay)
    {
        double tax = 0.40;
        return totalPay - (totalPay * tax);
    }

    public double calcAveragePay(int totalDays, double totalPay)
    {
        if (totalDays != 0)
        {
            return totalPay/totalDays;
        }
        return 0;
    }

    public double calcHoliday(double totalPay)
    {
        double holidayPercentage = 0.12;
        return totalPay * holidayPercentage;
    }


    public void homeAction()
    {
        Session.setPreviousWindow(null);
        Stage stage = new Stage();
        Parent root = null;
        try
        {
            root = FXMLLoader.load(getClass().getResource("/View/FXMLFrontPage_Window.fxml"));
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

    public void backAction()
    {
        //If the previous window was not NewJobWindow
        if (Session.getPreviousWindow() == null)
        {
            homeAction();
        }
        else
        {
            //If the previous window was the create window, return to it.
            Session.setPreviousWindow(null);
            Stage stage = new Stage();
            Parent root = null;
            try
            {
                root = FXMLLoader.load(getClass().getResource("/View/FXMLNewJobPage.fxml"));
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

}
