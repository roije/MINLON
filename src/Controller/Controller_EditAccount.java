package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

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
    private PasswordField currentPassField;

    @FXML
    private PasswordField newPassField;

    @FXML
    private PasswordField repeatPassField;

    @FXML
    private Button changePassBtn;

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
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
}
