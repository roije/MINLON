package Database;

import Model.Day;
import com.mysql.jdbc.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.text.DecimalFormat;

/**
 * Created by roije on 04/07/2016.
 */
public class DB_JobOverviewHandler
{
    private Connection databaseConnector = DB_Connector.getConnection();

    public ObservableList getDays(int jobId)
    {
        ResultSet rs;
        ObservableList<Day> days = FXCollections.observableArrayList();

        try
        {
            String sqlString = "SELECT dayName, dayDate, totalHours, hoursReg, firstOver, secondOver, saturday, " +
                    "sunday, totalPay " +
                    "FROM Days WHERE jobId = '"+jobId+"' ORDER BY dayDate ASC";
            rs = databaseConnector.createStatement().executeQuery(sqlString);
            try
            {
                while (rs.next())
                {
                    Day day = new Day();
                    day.setDayName(rs.getString("dayName"));
                    day.setDayDate(rs.getString("dayDate"));
                    day.setHoursTotal(rs.getInt("totalHours"));
                    day.setHoursReg(rs.getInt("hoursReg"));
                    day.setFirstOver(rs.getInt("firstOver"));
                    day.setSecondOver(rs.getInt("secondOver"));
                    day.setSaturdayHour(rs.getInt("saturday"));
                    day.setSundayHour(rs.getInt("sunday"));

                    //Set total and calculate after tax
                    double tax = 0.40;
                    double totalPay = rs.getDouble("totalPay");
                    double afterTax = totalPay - (totalPay * tax);

                    //I only want to decimals for the float numbers
                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    day.setTotalPay(numberFormat.format(totalPay).replace(",","."));
                    day.setAfterTax(numberFormat.format(afterTax).replace(",","."));

                    days.addAll(day);
                }

            }
            catch (Exception e)
            {

            }
        }
        catch (Exception e)
        {

        }
        return days;
    }
}
