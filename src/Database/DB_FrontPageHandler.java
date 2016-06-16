package Database;

import Model.Job;
import Model.User;
import com.mysql.jdbc.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

/**
 * Created by roije on 16/06/2016.
 */
public class DB_FrontPageHandler
{
    private Connection databaseConnector = DB_Connector.getConnection();

    public ObservableList<Job> getJobs(User user)
    {
        int userId = user.getUserId();
        ObservableList<Job> returnData = FXCollections.observableArrayList();
        ResultSet rs = null;

        //Find jobs in database and add to resultset
        try
        {
            String sqlString = "SELECT * FROM Jobs WHERE userId = '"+userId+"' ";
            rs = databaseConnector.createStatement().executeQuery(sqlString);

            //Add resultset data to observeablelist
            while (rs.next())
            {
                Job job = new Job();
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
                returnData.add(job);
            }
        }
        catch (Exception e)
        {

        }
        return returnData;
    }
}
