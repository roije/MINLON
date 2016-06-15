package Model;

/**
 * Created by roije on 15/06/2016.
 */
public class User
{
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private int loginCount;

    public User()
    {

    }

    public User(int userId, String firstName, String lastName, String username, int loginCount)
    {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.loginCount = loginCount;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUsername()
    {
        return username;
    }

    public int getLoginCount()
    {
        return loginCount;
    }

    public void setLoginCount(int loginCount)
    {
        this.loginCount = loginCount;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }


}
