package Controller;

import Database.DB_NewJobHandler;
import Model.Session;
import View.CurrentStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by roije on 15/06/2016.
 */
public class Controller_NewJob implements Initializable
{
    private DB_NewJobHandler newJobHandler = new DB_NewJobHandler();

    @FXML
    Button backBtnText, backBtnLogo, saveBtn;

    @FXML
    TextField workNameField, regularPayField, firstOverField, secondOverField, saturdayField, sundayField;

    @FXML
    HBox errorBox;

    @FXML
    Label errorLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

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

    public void saveBtnAction()
    {
        //Replace the "," to "." in order to save it in database
        String regularOver = regularPayField.getText();
        String regularFormatted = regularOver.replace(",",".");
        String firstOver = firstOverField.getText();
        String firstOverStrFormatted = firstOver.replace(",",".");
        String secondOver = secondOverField.getText();
        String secondOverFormatted = secondOver.replace(",",".");
        String saturdayOver = saturdayField.getText();
        String saturdayFormatted = saturdayOver.replace(",",".");
        String sundayover = sundayField.getText();
        String sundayFormatted = sundayover.replace(",",".");

        String name = workNameField.getText();

        if (isValidInput(regularFormatted, firstOverStrFormatted,
                secondOverFormatted, saturdayFormatted, sundayFormatted) && validJobName(name))
        {
            double regularPaydoub = Double.parseDouble(regularFormatted);
            double firstOverdoub = Double.parseDouble(firstOverStrFormatted);
            double secondOverdoub = Double.parseDouble(secondOverFormatted);
            double saturdaydoub = Double.parseDouble(saturdayFormatted);
            double sundaydoub = Double.parseDouble(sundayFormatted);

            //Save in database.
            //Set the previous window string in Session class to "newjob". The reason for this is that I want
            //the user to come back to job creation after he has created a new job and is in job page.
            //newJobHandler gets the latest element in the database (this job) and makes it the current job in Session
            newJobHandler.saveJob(name,regularPaydoub,firstOverdoub,secondOverdoub,saturdaydoub,sundaydoub);
            Session.setPreviousWindow("newjob");
            Session.setCurrentJob(newJobHandler.getNewestJob());
            changeToJobPageWindow();

        }
        else if (!validJobName(name))
        {
            errorLabel.setText("Feilur í arbeiðsnavnið");
            errorBox.setVisible(true);
        }
        else
        {
            errorLabel.setText("Lønarteigir kunnu bert innihalda tøl og komma/punktum");
            errorBox.setVisible(true);
        }
    }

    public boolean isValidInput(String regular, String firstOver, String secondOver, String saturday, String sunday)
    {
        //Contains only numbers in range 0 to 9 and one dot
        String regex = "^[0-9]*\\.?[0-9]*$";

        if (!regular.isEmpty() && regular.matches(regex) && !regular.startsWith(".") && !regular.endsWith(".") &&
                !firstOver.isEmpty() && firstOver.matches(regex) && !firstOver.startsWith(".") && !firstOver.endsWith(".") &&
                !secondOver.isEmpty() && secondOver.matches(regex) && !secondOver.startsWith(".") && !secondOver.endsWith(".") &&
                !saturday.isEmpty() && saturday.matches(regex) && !saturday.startsWith(".") && !saturday.endsWith(".") &&
                !sunday.isEmpty() && sunday.matches(regex) && !sunday.startsWith(".") && !sunday.endsWith("."))
        {
            return true;
        }
        return false;
    }

    public boolean validJobName(String jobName)
    {
        int maxlen = 60;
        if (!jobName.isEmpty() && jobName.length() <= maxlen)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void clearErrorBox()
    {
        errorBox.setVisible(false);
    }

    public void changeToJobPageWindow()
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
