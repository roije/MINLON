package Controller;

import Database.DB_EditAccountHandler;
import Model.Session;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by roije on 31/05/2016.
 */
public class Controller_EditAccount implements Initializable
{
    @FXML
    private VBox changePassBox;

    @FXML
    private Label currentPassLabel;

    @FXML
    private Label newPassLabel;

    @FXML
    private Label repeatPassLabel;

    @FXML
    private TextField firstNameField, lastNameField;

    @FXML
    private PasswordField currentPassField;

    @FXML
    private PasswordField newPassField;

    @FXML
    private PasswordField repeatPassField;

    @FXML
    private Button changePassBtn, backBtnLogo, backBtnText;

    @FXML
    private ImageView imageView;

    private DB_EditAccountHandler editAccountHandler = new DB_EditAccountHandler();

    private boolean changePassBtnActive= false;

    @FXML
    private Label errorLabel;

    @FXML
    private ImageView errorIcon;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        firstNameField.setText(editAccountHandler.getUserFirstName(Session.getCurrentUser().getUserId()));
        lastNameField.setText(editAccountHandler.getUserLastName(Session.getCurrentUser().getUserId()));
        changePassBox.setPrefHeight(0);
        currentPassLabel.setVisible(false);
        newPassLabel.setVisible(false);
        repeatPassLabel.setVisible(false);
        currentPassField.setVisible(false);
        newPassField.setVisible(false);
        repeatPassField.setVisible(false);
    }

    //Shows or hides the VBOX containing the labels and field for changing a password
    //If the height is 0, which it is when this window is initialized, change the height to 140
    //and show all fields, and change the Image of the ImageView. Else set it the the initiliez form.
    public void changePassButtonAction()
    {
        if (changePassBox.getPrefHeight() == 0)
        {
            iconSpecification("/Icons/CollapseArrow-48.png");

            changePassBtnActive = true;
            changePassBox.setPrefHeight(140);
            currentPassLabel.setVisible(true);
            newPassLabel.setVisible(true);
            repeatPassLabel.setVisible(true);
            currentPassField.setVisible(true);
            newPassField.setVisible(true);
            repeatPassField.setVisible(true);
        }
        else
        {
            iconSpecification("/Icons/ExpandArrow-48.png");

            changePassBtnActive = false;
            changePassBox.setPrefHeight(0);
            currentPassLabel.setVisible(false);
            newPassLabel.setVisible(false);
            repeatPassLabel.setVisible(false);
            currentPassField.setVisible(false);
            newPassField.setVisible(false);
            repeatPassField.setVisible(false);
            currentPassField.clear();
            newPassField.clear();
            repeatPassField.clear();

        }
    }

    public void iconSpecification(String imgName)
    {
        Image image = new Image(imgName);
        imageView.setImage(image);
        imageView.setFitHeight(18);
        imageView.setFitWidth(19);
    }

    public void saveAction()
    {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String password = currentPassField.getText();
        String firstPass = newPassField.getText();
        String repeatPass = repeatPassField.getText();

        //If change password area is not visible do this:
        if (!changePassBtnActive)
        {
            editAccountHandler.saveUpdates(firstName, lastName);
            changeToPreviousWindow();

        }
        //If change password are is visible, do this:
        else
        {
            if(editAccountHandler.isCorrectPassword(password) && passwordsMatch(firstPass, repeatPass))
            {
                editAccountHandler.saveUpdates(firstName, lastName, firstPass);
                changeToPreviousWindow();
            }
            else
            {
                errorLabel.setText("Feilur í innskrivaðu loyniorðum");
                errorLabel.setVisible(true);
                errorIcon.setVisible(true);
            }
        }
    }

    public boolean passwordsMatch(String first, String confirm)
    {
        if (first.equals(confirm))
            return true;
        else
            return false;
    }

    public void removeErrorStuff()
    {
        errorLabel.setVisible(false);
        errorIcon.setVisible(false);
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
