package hu.iit.uni.miskolc.webalk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * c = DriverManager.getConnection("jdbc:sqlite:/home/dani/IdeaProjects/WebAlkBeadando/dao/src/resources/glassShop.db");
 * c = DriverManager.getConnection("jdbc:sqlite:C:/Users/Dani/IdeaProjects/WebAlkBeadando/dao/src/resources/glassShop.db");
 */
public class App {
    public static void main(String[] args) {
        String con = "jdbc:sqlite:C:/Users/Dani/IdeaProjects/WebAlkBeadando/dao/src/resources/glassShop.db";
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully\n\n\nShops:");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Shop;");

            while (rs.next()) {
                String name = rs.getString("NAME");
                String location = rs.getString("LOCATION");

                System.out.println("Name = " + name);
                System.out.println("Location = " + location);
                System.out.println();
            }

            System.out.println("Employee:");
            rs = stmt.executeQuery("SELECT * FROM Employee;");
            while (rs.next()) {
                int id = rs.getInt("IDNUM");
                String name = rs.getString("NAME");
                String gender = rs.getString("GENDER");
                float salary = rs.getFloat("SALARY");
                String post = rs.getString("POST");
                String shopName = rs.getString("ShopName");

                System.out.println("IdNum = " + id);
                System.out.println("Name = " + name);
                System.out.println("Gender = " + gender);
                System.out.println("Salary = " + salary);
                System.out.println("Post = " + post);
                System.out.println("ShopName = " + shopName);
                System.out.println();
            }

            System.out.println("Glasses");
            rs = stmt.executeQuery("SELECT * FROM Glasses");
            while (rs.next()) {
                String brand = rs.getString("Brand");
                String model = rs.getString("Model");
                float price = rs.getFloat("Price");
                String gender = rs.getString("Gender");
                boolean sunglasses = (rs.getInt("Sunglasses")) > 0 ? true : false;
                String availableAt = rs.getString("AvailableAt");

                System.out.println("Brand = " + brand);
                System.out.println("Model = " + model);
                System.out.println("Price = " + price);
                System.out.println("Gender = " + gender);
                System.out.println("Sunglasses = " + sunglasses);
                System.out.println("AvailableAt = " + availableAt);
                System.out.println();
            }

            System.out.println("Accessories");
            rs = stmt.executeQuery("SELECT * FROM Accessories");
            while (rs.next()) {
                String appellation = rs.getString("Appellation");
                String brand = rs.getString("Brand");
                float price = rs.getFloat("Price");

                System.out.println("Appellation = " + appellation);
                System.out.println("Brand = " + brand);
                System.out.println("Price = " + price);
                System.out.println();
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");

    }

    public static void createTable(String sql, String con) {
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
        /*String a = "CREATE TABLE Accessories(" +
                "  Appellation CHAR(30) NOT NULL," +
                "  Brand CHAR(25) NOT NULL," +
                "  Price FLOAT NOT NULL)";
        String b = "CREATE TABLE Glasses(" +
                "  Brand CHAR(30) NOT NULL," +
                "  Model CHAR(30) NOT NULL," +
                "  Price FLOAT NOT NULL," +
                "  Gender CHAR(30) NOT NULL," +
                "  Sunglasses INT NOT NULL," +
                "  AvailableAt CHAR(50) NOT NULL," +
                "  FOREIGN KEY (AvailableAt) REFERENCES Shop(NAME))";
        String shop =
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

    public static void insertInto(String con) {
        /*
        * insertInto(con);
        * System.out.println("Insert DONE!\n");
        */
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO Accessories (Appellation,Brand,Price) " +
                    "VALUES ('Tok', 'Oakley', 2000.0 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Accessories (Appellation,Brand,Price)" +
                    "VALUES ('Tisztító Kendő', 'Hoya', 1500.0 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Glasses (Brand,Model,Price,Gender,Sunglasses,AvailableAt) " +
                    "VALUES ('Oakley', 'Batwolf', 35000.0, 'MALE', 1, 'Optiris');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Glasses (Brand,Model,Price,Gender,Sunglasses,AvailableAt) " +
                    "VALUES ('Ray-Ban', 'Aviator' , 30000.0, 'MALE', 0, 'Trend-Opt');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
