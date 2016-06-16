package Model;

/**
 * Created by roije on 16/06/2016.
 */
public class Job
{
    private int jobId;
    private String jobName;
    private double regularPay;
    private double firstOverPay;
    private double secondOverPay;
    private double saturdayPay;
    private double sundayPay;
    private double totalPay;
    private int totalDays;
    private int totalHours;
    private int userId;

    public Job()
    {

    }

    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public int getJobId()
    {
        return jobId;
    }

    public void setJobId(int jobId)
    {
        this.jobId = jobId;
    }

    public double getRegularPay()
    {
        return regularPay;
    }

    public void setRegularPay(double regularPay)
    {
        this.regularPay = regularPay;
    }

    public double getFirstOverPay()
    {
        return firstOverPay;
    }

    public void setFirstOverPay(double firstOverPay)
    {
        this.firstOverPay = firstOverPay;
    }

    public double getSecondOverPay()
    {
        return secondOverPay;
    }

    public void setSecondOverPay(double secondOverPay)
    {
        this.secondOverPay = secondOverPay;
    }

    public double getSaturdayPay()
    {
        return saturdayPay;
    }

    public void setSaturdayPay(double saturdayPay)
    {
        this.saturdayPay = saturdayPay;
    }

    public double getSundayPay()
    {
        return sundayPay;
    }

    public void setSundayPay(double sundayPay)
    {
        this.sundayPay = sundayPay;
    }

    public double getTotalPay()
    {
        return totalPay;
    }

    public void setTotalPay(double totalPay)
    {
        this.totalPay = totalPay;
    }

    public int getTotalDays()
    {
        return totalDays;
    }

    public void setTotalDays(int totalDays)
    {
        this.totalDays = totalDays;
    }

    public int getTotalHours()
    {
        return totalHours;
    }

    public void setTotalHours(int totalHours)
    {
        this.totalHours = totalHours;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }
}
