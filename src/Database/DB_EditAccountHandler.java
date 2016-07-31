package Database;

import Model.Session;
import Model.User;
import Security.BCrypt;
import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by roije on 10/07/2016.
 */
public class DB_EditAccountHandler
{
    //Connector to Database
    private Connection databaseConnector = DB_Connector.getConnection();

    public String getUserFirstName(int userId)
    {
        String firstname = "";
        ResultSet rs;
        String sqlString = "SELECT firstName FROM Users WHERE userId = '"+userId+"'";
        try
        {
            rs = databaseConnector.createStatement().executeQuery(sqlString);
            if (rs.next())
            {
                firstname = rs.getString("firstName");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
       return firstname;
    }

    public String getUserLastName(int userId)
    {
        String lastname = "";
        ResultSet rs;
        String sqlString = "SELECT lastName FROM Users WHERE userId = '"+userId+"'";
        try
        {
            rs = databaseConnector.createStatement().executeQuery(sqlString);
            if (rs.next())
            {
                lastname = rs.getString("lastName");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return lastname;
    }

    public boolean isCorrectPassword(String password)
    {
        ResultSet rs;
        String generatedPassHash = null;
        String sqlQuery = "SELECT * FROM Users WHERE userId = '"+Session.getCurrentUser().getUserId()+"'";
        try
        {
            rs = databaseConnector.createStatement().executeQuery(sqlQuery);
            if (rs.next())
            {
                generatedPassHash = rs.getString("passwordHash");
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

    public void saveUpdates(String firstName, String lastName)
    {
        String sqlString = "UPDATE Users SET firstName = ?, lastName = ? " +
                "WHERE userId = ? ";
        try
        {
            PreparedStatement preparedStatement = databaseConnector.prepareStatement(sqlString);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, Session.getCurrentUser().getUserId());
            preparedStatement.executeUpdate();
            Session.getCurrentUser().setFirstName(firstName);
            Session.getCurrentUser().setLastName(lastName);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void saveUpdates(String firstName, String lastName, String password)
    {
        String encryptPass = BCrypt.hashpw(password, BCrypt.gensalt(12));
        String sqlString = "UPDATE Users SET firstName = ?, lastName = ?, passwordHash = ? " +
                "WHERE userId = ? ";
        try
        {
            PreparedStatement preparedStatement = databaseConnector.prepareStatement(sqlString);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, encryptPass);
            preparedStatement.setInt(4, Session.getCurrentUser().getUserId());
            preparedStatement.executeUpdate();
            Session.getCurrentUser().setFirstName(firstName);
            Session.getCurrentUser().setLastName(lastName);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


}
