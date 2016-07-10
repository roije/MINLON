package Model;

import java.text.DecimalFormat;

/**
 * Created by roije on 10/07/2016.
 * This class is only used in the account window.
 * To reduce the amount of sql queries made in DB_AccountWindowHandler, one query is set, where all the fields below
 * are given a value
 */
public class BestJob
{
    private String name;
    private int hours;
    private double pay;
    private String  dayAverage;
    private int days;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getHours()
    {
        return hours;
    }

    public void setHours(int hours)
    {
        this.hours = hours;
    }

    public double getPay()
    {
        return pay;
    }

    public void setPay(int pay)
    {
        this.pay = pay;
    }

    public String getDayAverage()
    {
        return dayAverage;
    }

    public void setDayAverage()
    {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        this.dayAverage = numberFormat.format(getPay()/getDays()).replace(",",".");
    }

    public int getDays()
    {
        return days;
    }

    public void setDays(int days)
    {
        this.days = days;
    }
}
