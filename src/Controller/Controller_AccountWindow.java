package Controller;

import Database.DB_AccountWindowHandler;
import Model.BestJob;
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
 * Created by roije on 10/07/2016.
 */
public class Controller_AccountWindow implements Initializable
{
    private DB_AccountWindowHandler accountWindowHandler = new DB_AccountWindowHandler();

    @FXML
    Label nameLabel, usernameLabel, amountJobsLabel, loginCountLabel;

    @FXML
    Label totalHoursLabel, totalPayLabel, bestJobLabel, mostHoursJobLabel;

    @FXML
    Label bestJobNameLabel, bestJobHoursLabel, bestJobPayLabel, bestJobAverageLabel;

    @FXML
    Button backBtnLogo, backBtnText;

    private DecimalFormat numberFormat = new DecimalFormat("#.00");


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //Set userinformation labels
        //Blue fields
        int userId = Session.getCurrentUser().getUserId();
        String firstName = Session.getCurrentUser().getFirstName();
        String lastName = Session.getCurrentUser().getLastName();
        String username = Session.getCurrentUser().getUsername();
        int loginCount = Session.getCurrentUser().getLoginCount();
        nameLabel.setText(firstName + " " + lastName);
        usernameLabel.setText(username);
        amountJobsLabel.setText(String.valueOf(accountWindowHandler.getUserJobCount(userId)));
        loginCountLabel.setText(String.valueOf(loginCount));

        //Orange fields
        totalHoursLabel.setText(String.valueOf(accountWindowHandler.getUserTotalHours(userId)));
        totalPayLabel.setText(numberFormat.format(accountWindowHandler.getUserTotalPay(userId)).replace(",","."));
        bestJobLabel.setText(accountWindowHandler.getUserBestJobName(userId));
        mostHoursJobLabel.setText(accountWindowHandler.getUserJobMostHours(userId));

        //Turquoise fields
        accountWindowHandler.getBestJobInfo(userId);
        BestJob bestJob = accountWindowHandler.getBestJob();
        bestJobNameLabel.setText(bestJob.getName());
        bestJobHoursLabel.setText(String.valueOf(bestJob.getHours()));
        bestJobPayLabel.setText(numberFormat.format(bestJob.getPay()).replace(",","."));
        if (!bestJobHoursLabel.getText().equals("0"))
        {
            bestJobAverageLabel.setText(bestJob.getDayAverage());
        }
        else
        {
            bestJobAverageLabel.setText("0.0");
        }
    }

    public void changeToPreviousWindow()
    {
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
}
