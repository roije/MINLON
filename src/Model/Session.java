package Model;

/**
 * Created by roije on 15/06/2016.
 */

public class Session
{
    private static User currentUser;
    private static Job currentJob;

    //This string is used when the user is creating jobs
    //Its used to check if the last window was null or some string.
    //It is not null, return to previous window (NewJob) or return to FrontPage
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
