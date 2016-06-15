package View;

import javafx.stage.Stage;

/**
 * Created by roije on 12/06/2016.
 */
public class CurrentStage
{
    private static Stage thisStage;

    public static void setCurrentStage (Stage stage)
    {
        thisStage = stage;
    }

    public static Stage getCurrentStage()
    {
        return thisStage;
    }

    public static void showCurrentStage()
    {
        thisStage.show();
    }

    public static void closeCurrentStage()
    {
        thisStage.close();;
    }
}
