package Model;

/**
 * Created by roije on 15/06/2016.
 */

public class Session
{
    private static User currentUser;
    private static Job currentJob;
    private static String previousWindow;


    public static void setCurrentUser(User user)
    {
        currentUser = user;
    }

    public static User getCurrentUser()
    {
        return currentUser;
    }

    public static Job getCurrentJob()
    {
        return currentJob;
    }

    public static void setCurrentJob(Job job)
    {
        currentJob = job;
    }

    public static String getPreviousWindow()
    {
        return previousWindow;
    }

    public static void setPreviousWindow(String window)
    {
        previousWindow = window;
    }
}
