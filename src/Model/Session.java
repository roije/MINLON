package Model;

/**
 * Created by roije on 15/06/2016.
 */

public class Session
{
    private static User currentUser;

    public static void setCurrentUser(User user)
    {
        currentUser = user;
    }

    public static User getCurrentUser()
    {
        return currentUser;
    }
}
