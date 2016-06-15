package Database;

import Model.Session;
import Model.User;
import Security.BCrypt;
import com.mysql.jdbc.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by roije on 15/06/2016.
 */
public class DB_LoginHandler
{
    //Connector to Database
    private Connection databaseConnector = DB_Connector.getConnection();

    public boolean userExists(String username, String password)
    {
        ResultSet rs;
        String generatedPassHash = null;

        String sqlQuery = "SELECT * FROM Users WHERE username = '"+username+"'";
        try
        {
            rs = databaseConnector.createStatement().executeQuery(sqlQuery);
            if (rs.next())
            {
                generatedPassHash = rs.getString("passwordHash");

                //New user for this session
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setUsername(rs.getString("username"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setLoginCount(rs.getInt("loginCount"));

                Session.setCurrentUser(user);
            }
            else
                return false;

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        boolean matched = BCrypt.checkpw(password, generatedPassHash);
        return matched;
    }

    public void updateLoginCount(String username)
    {
        String sqlQuery = "Update Users SET loginCount = loginCount + 1 WHERE username = '"+username+"'";
        try
        {
            databaseConnector.createStatement().executeUpdate(sqlQuery);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
