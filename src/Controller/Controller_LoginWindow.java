package Controller;

import View.CurrentStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by roije on 23/05/2016.
 */
public class Controller_LoginWindow implements Initializable
{
    @FXML
    ImageView loginError;

    @FXML
    Button createAccountBtn;

    @FXML
    Button signInBtn;

    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loginError.setVisible(false);
    }

    public void changeToNewAccountWindow() throws IOException
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/View/FXMLNewAccountPage_Window.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        CurrentStage.getCurrentStage().close();
        CurrentStage.setCurrentStage(stage);
        CurrentStage.showCurrentStage();
    }
}
