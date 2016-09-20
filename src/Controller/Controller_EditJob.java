package Controller;

import View.CurrentStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by roije on 31/07/2016.
 */
public class Controller_EditJob implements Initializable
{

    @FXML
    private Button backBtnText, backBtnIcon, saveBtn;

    @FXML
    private TextField jobNameField, regularPayField, firstOverField, secondOverField, saturdayPayField, sundayPayField;

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
