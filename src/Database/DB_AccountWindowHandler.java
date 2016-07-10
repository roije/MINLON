package Database;

import Model.BestJob;
import com.mysql.jdbc.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by roije on 10/07/2016.
 */
public class DB_AccountWindowHandler
{
    //Connector to Database
    private Connection databaseConnector = DB_Connector.getConnection();
    private BestJob bestJob = new BestJob();

    //T

    public int getUserJobCount(int userId)
    {
        int count = 0;
        ResultSet rs;
        String sqlString = "SELECT COUNT(*) AS jobCount FROM Jobs WHERE userId ='"+userId+"'";
        try
        {
            rs = databaseConnector.createStatement().executeQuery(sqlString);
            if (rs.next())
            {
                count = rs.getInt("jobCount");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return count;
    }

    public int getUserTotalHours(int userId)
    {
        int count = 0;
        ResultSet rs;
        String sqlString = "SELECT SUM(totalHours) AS totalHourUserCount FROM Jobs WHERE userId = '"+userId+"'";
        try
        {
            rs = databaseConnector.createStatement().executeQuery(sqlString);
            if (rs.next())
            {
                count = rs.getInt("totalHourUserCount");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return count;
    }

    public double getUserTotalPay(int userId)
    {
        double count = 0;
        ResultSet rs;
        String sqlString = "SELECT SUM(totalPay) AS totalPayUser FROM Jobs WHERE userId = '"+userId+"'";
        try
        {
            rs = databaseConnector.createStatement().executeQuery(sqlString);
            if (rs.next())
            {
                count = rs.getDouble("totalPayUser");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return count;
    }

    public String getUserBestJobName(int userId)
    {
        String jobName = "";
        ResultSet rs;
        String sqlString = "SELECT jobName FROM Jobs WHERE totalPay = " +
                "(SELECT MAX(totalPay) FROM Jobs WHERE userId = '"+userId+"') AND userId = '"+userId+"' ";
        try
        {
            rs = databaseConnector.createStatement().executeQuery(sqlString);
            if (rs.next())
            {
                jobName = rs.getString("jobName");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return jobName;
    }

    public String getUserJobMostHours(int userId)
    {
        String jobName = "";
        ResultSet rs;
        String sqlString = "SELECT jobName FROM Jobs WHERE totalHours = " +
                "(SELECT MAX(totalHours) FROM Jobs WHERE userId = '"+userId+"') AND userId = '"+userId+"' ";
        try
        {
            rs = databaseConnector.createStatement().executeQuery(sqlString);
            if (rs.next())
            {
                jobName = rs.getString("jobName");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return jobName;
    }

    public void getBestJobInfo(int userId)
    {
        ResultSet rs;
        String sqlString = "SELECT * FROM Jobs WHERE totalPay = " +
                "(SELECT MAX(totalPay) FROM Jobs WHERE userId = '"+userId+"') AND userId = '"+userId+"' ";
        try
        {
            rs = databaseConnector.createStatement().executeQuery(sqlString);
            if (rs.next())
            {
                bestJob.setName(rs.getString("jobName"));
                bestJob.setHours(rs.getInt("totalHours"));
                bestJob.setDays(rs.getInt("totalDays"));
                bestJob.setPay(rs.getInt("totalPay"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        bestJob.setDayAverage();
    }

    public BestJob getBestJob()
    {
        return bestJob;
    }
}
