package Database;

import Model.Job;
import Model.Session;
import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by roije on 15/06/2016.
 */
public class DB_NewJobHandler
{
    private Connection databaseConnector = DB_Connector.getConnection();

    public void saveJob(String jobName, double regularPay, double firstOver, double secondOver, double saturday,
                        double sunday)
    {
        String sqlQuery = "INSERT INTO Jobs (jobName, regularPay, firstOverPay, secondOverPay, saturdayPay, sundayPay, userId)" +
                "VALUES (?, ?, ?, ?, ? ,?, ?)";

        try
        {
            PreparedStatement preparedStatement = databaseConnector.prepareStatement(sqlQuery);
            preparedStatement.setString(1, jobName);
            preparedStatement.setDouble(2, regularPay);
            preparedStatement.setDouble(3, firstOver);
            preparedStatement.setDouble(4, secondOver);
            preparedStatement.setDouble(5, saturday);
            preparedStatement.setDouble(6, sunday);
            preparedStatement.setInt(7, Session.getCurrentUser().getUserId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Job getNewestJob()
    {
        String sqlQuery = "SELECT * FROM Jobs ORDER BY jobId DESC LIMIT 1";
        ResultSet rs;
        Job job = new Job();
        try
        {
            rs = databaseConnector.createStatement().executeQuery(sqlQuery);
            if (rs.next())
            {
                job.setJobName(rs.getString("jobName"));
                job.setJobId(rs.getInt("jobId"));
                job.setRegularPay(rs.getDouble("regularPay"));
                job.setFirstOverPay(rs.getDouble("firstOverPay"));
                job.setSecondOverPay(rs.getDouble("secondOverPay"));
                job.setSaturdayPay(rs.getDouble("saturdayPay"));
                job.setSundayPay(rs.getDouble("sundayPay"));
                job.setTotalPay(rs.getDouble("totalPay"));
                job.setTotalDays(rs.getInt("totalDays"));
                job.setTotalHours(rs.getInt("totalHours"));
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return job;
    }
}
