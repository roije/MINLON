package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by roije on 23/05/2016.
 */
public class Controller_LoginWindow implements Initializable
{
    @FXML
    ImageView loginError;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loginError.setVisible(false);
    }
}
