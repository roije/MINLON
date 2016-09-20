package RunPackage;

import Database.DB_CreateDatabase;
import Database.DB_Tables;
import Security.BCrypt;
import View.CurrentStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by roije on 21/05/2016.
 */
public class RunApp extends Application
{
    public static void main(String[] args)
    {
        /*
        String  originalPassword = "roi0x055";
        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
        System.out.println(BCrypt.gensalt());
        System.out.println(generatedSecuredPasswordHash + " " + generatedSecuredPasswordHash.length());

        boolean matched = BCrypt.checkpw("roiroi", generatedSecuredPasswordHash);
        System.out.println(matched);
        */

        DB_CreateDatabase.create();
        DB_Tables.create();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/View/FXMLLogin_Window.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        CurrentStage.setCurrentStage(primaryStage);
        CurrentStage.showCurrentStage();
    }
}
