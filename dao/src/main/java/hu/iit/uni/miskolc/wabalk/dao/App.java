package hu.iit.uni.miskolc.wabalk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *c = DriverManager.getConnection(
 * "jdbc:sqlite:/home/dani/IdeaProjects/WebAlkBeadando/dao/src/resources/glassShop.db");
 */
public class App 
{
    public static void main( String[] args )
    {
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/home/dani/IdeaProjects/WebAlkBeadando/dao/src/resources/glassShop.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Shop;" );

            while ( rs.next() ) {
                String  name = rs.getString("NAME");
                String  location = rs.getString("LOCATION");

                System.out.println( "Name = " + name );
                System.out.println( "Location = " + location );
                System.out.println();
            }

            rs = stmt.executeQuery("SELECT * FROM Employee;");
            while ( rs.next() ) {
                int id = rs.getInt("IDNUM");
                String  name = rs.getString("NAME");
                String gender = rs.getString("GENDER");
                float salary = rs.getFloat("SALARY");
                String post = rs.getString("POST");
                String shopname = rs.getString("ShopName");

                System.out.println("IdNum = " + id);
                System.out.println("Name = " + name);
                System.out.println("Gender = " + gender);
                System.out.println("Salary = " + salary);
                System.out.println("Post = " + post);
                System.out.println("ShopName = " + shopname);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

            /*String shop =
                    "CREATE TABLE Employee(" +
                    "IDNUM INT NOT NULL," +
                    "NAME CHAR(50) NOT NULL," +
                    "GENDER CHAR(10) NOT NULL," +
                    "SALARY FLOAT NOT NULL," +
                    "POST CHAR(20) NOT NULL," +
                    "ShopName CHAR(50) NOT NULL," +
                    "PRIMARY KEY (IDNUM)," +
                    "FOREIGN KEY (ShopName) REFERENCES Shop(NAME))";*/
    }
}
