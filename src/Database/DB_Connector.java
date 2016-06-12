package Database;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;

/**
 * Created by roije on 12/06/2016.
 */
public class DB_Connector
{
    final static String user = "root";
    final static String pass = "admin";
    final static String db = DB_CreateDatabase.DBNAME;
    final static String url = "jdbc:mysql://localhost/";

    public static Connection getConnection()
    {
        Connection conn;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url + db, user, pass);
            return conn;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
