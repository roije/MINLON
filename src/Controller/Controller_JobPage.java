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
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * Created by roije on 16/06/2016.
 */
public class Controller_JobPage implements Initializable
{
    @FXML
    Button backBtnLogo, backBtnText, homeBtnLogo, homeBtnText, newDayBtn, overviewBtn, editBtn;

    @FXML
    Label regularLabel, firstOverLabel, secondOverLabel, saturdayLabel, sundayLabel, jobNameLabel;

    @FXML
    Label totalHoursLabel, totalDaysLabel, beforeTaxLabel, afterTaxLabel, averageLabel, holidayLabel;

    DecimalFormat numberFormat = new DecimalFormat("#.00");


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

        double beforeTax = Session.getCurrentJob().getTotalPay();
        String beforeTaxString = numberFormat.format(beforeTax).replace(",",".");
        beforeTaxLabel.setText(beforeTaxString);

        afterTaxLabel.setText(calcTaxPay(Session.getCurrentJob().getTotalPay()));

        averageLabel.setText(String.valueOf(calcAveragePay(Session.getCurrentJob().getTotalDays(),
                Session.getCurrentJob().getTotalPay())));
        holidayLabel.setText(String.valueOf(calcHoliday(Session.getCurrentJob().getTotalPay())));


        if (Session.getCurrentJob().getTotalDays() == 0)
        {
            afterTaxLabel.setText("0.0");
            averageLabel.setText("0.0");
            holidayLabel.setText("0.0");
        }
    }

    public String calcTaxPay(double totalPay)
    {
        String returnString;
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        double tax = 0.40;
        returnString = numberFormat.format(totalPay - (totalPay * tax)).replace(",",".");
        return returnString;
    }

    public String calcAveragePay(int totalDays, double totalPay)
    {
        String returnString;
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        if (totalDays != 0)
        {
            returnString = numberFormat.format(totalPay/totalDays).replace(",",".");
            return returnString;
        }
        return "0.0";
    }

    public String calcHoliday(double totalPay)
    {
        String returnString;
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        double holidayPercentage = 0.12;
        returnString = numberFormat.format(totalPay * holidayPercentage).replace(",",".");
        return returnString;
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
            //If the previous window was the create window, return to NewJobWindow.
            //Set previous window to null
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

    public void changeToNewDayPage()
    {
        Stage stage = new Stage();
        Parent root = null;
        try
        {
            root = FXMLLoader.load(getClass().getResource("/View/FXMLNewDayPage_Window.fxml"));
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

    public void changeToJobOverviewWindow()
    {
        Stage stage = new Stage();
        Parent root = null;
        try
        {
            root = FXMLLoader.load(getClass().getResource("/View/FXMLJobOverview_Window.fxml"));
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
