package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by roije on 12/06/2016.
 */
public class DB_CreateDatabase
{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "admin";
    static final String DBNAME = "MinLonDB";

    public static void create()
    {
        Connection conn;
        Statement stmt;
        try
        {
            //STEP 2: Register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();

            String sql = "CREATE DATABASE IF NOT EXISTS MinLonDB";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (Exception se)
        {
            //Handle errors for JDBC
        }
    }
}
