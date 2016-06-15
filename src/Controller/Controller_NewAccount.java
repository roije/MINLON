package Controller;

import Database.DB_UserAccountHandler;
import Security.BCrypt;
import View.CurrentStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by roije on 12/06/2016.
 */
public class Controller_NewAccount implements Initializable
{

    //This object has methods to work on account table in database
    private DB_UserAccountHandler accountHandler = new DB_UserAccountHandler();


    @FXML
    Button saveBtn, backBtnText, backBtnLogo, toLoginPageBtn;

    @FXML
    TextField firstNameField, lastNameField, userNameField;

    @FXML
    PasswordField passwordField, confirmPasswordField;

    @FXML
    ImageView userNameError, passwordError;

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
            root = FXMLLoader.load(getClass().getResource("/View/FXMLLogin_Window.fxml"));
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
        //Turn fields in GUI to right datatype, so that they can be saved in database.
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = userNameField.getText();
        String password = passwordField.getText();
        String confPassword = confirmPasswordField.getText();

        if (fieldIsEmpty(firstName,lastName,username,password,confPassword))
        {
            errorLabel.setText("Vinarliga útfyll øll feltir");
            errorLabel.setVisible(true);
        }
        else if(accountHandler.userExists(username))
        {
            errorLabel.setText("Brúkaranavn er ikki tøkt");
            errorLabel.setVisible(true);
            userNameError.setVisible(true);
        }
        else if(!passwordsMatch(password, confPassword))
        {
            errorLabel.setText("Innskrivaðu loyniorð passa ikki saman");
            errorLabel.setVisible(true);
            passwordError.setVisible(true);
        }
        else
        {
            String encryptPass = BCrypt.hashpw(password, BCrypt.gensalt(12));
            accountHandler.saveUser(firstName, lastName, username, encryptPass);
            saveBtn.setDisable(true);
            try
            {
                openPopUp();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    //Clear error image when user name textfield is clicked
    public void clearErrors()
    {
        userNameError.setVisible(false);
        passwordError.setVisible(false);
        errorLabel.setVisible(false);
    }

    public boolean passwordsMatch(String password, String confirmPassword)
    {
        if (password.equals(confirmPassword))
            return true;
        else return false;
    }

    public void openPopUp() throws IOException
    {
        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("/View/FXMLAccountCreated_Popup.fxml"));
        Scene scene = new Scene(root);
        popupStage.setScene(scene);
        popupStage.show();
    }

    public void closePopUp()
    {
        //Change to login page
        changeToPreviousWindow();

        Stage popupStage = (Stage) toLoginPageBtn.getScene().getWindow();
        popupStage.close();
    }

    public boolean fieldIsEmpty(String firstName, String lastName, String userName, String password, String confirmPass)
    {
        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || password.isEmpty() || confirmPass.isEmpty())
            return true;
        else return false;
    }
}
