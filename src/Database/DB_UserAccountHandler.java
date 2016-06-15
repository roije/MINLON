package Database;

import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by roije on 12/06/2016.
 */
public class DB_UserAccountHandler
{
    //Connector to Database
    private Connection databaseConnector = DB_Connector.getConnection();

    //Check if a user exists with username.
    //Username from TextField in GUI as a string.
    public boolean userExists(String username)
    {
        ResultSet rs;

        String sqlQuery = "SELECT * FROM Users WHERE username = '"+username+"'";
        try
        {
            rs = databaseConnector.createStatement().executeQuery(sqlQuery);
            if (rs.next())
            {
                return true;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public void saveUser(String firstName, String lastName, String userName, String password)
    {
        String sqlQuery = "INSERT INTO Users (firstName, lastName, username, passwordHash)" +
                "VALUES (?, ?, ?, ?)";
        try
        {
            PreparedStatement preparedStatement = databaseConnector.prepareStatement(sqlQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
