package hu.iit.uni.miskolc.wabalk.dao;

import hu.iit.uni.miskolc.wabalk.service.dao.ShopDAO;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoEmployeeException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoLocationException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;
import hu.iit.uni.miskolc.webalk.core.model.Employee;
import hu.iit.uni.miskolc.webalk.core.model.Shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class ShopDAOsql implements ShopDAO {

    @Override
    public void createShop(Shop shop) {
        String con = "jdbc:sqlite:C:/Users/Dani/IdeaProjects/WebAlkBeadando/dao/src/resources/glassShop.db";
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO Shop (Name, Location) VALUES (" + shop.getName() + ", " + shop.getLocation() + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        }catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public Shop getShopByName(String name) throws NoEmployeeException, NoNameException, NoLocationException {
        String con = "jdbc:sqlite:C:/Users/Dani/IdeaProjects/WebAlkBeadando/dao/src/resources/glassShop.db";
        Connection c;
        Statement stmt;
        Collection<Employee> employees = new ArrayList<>();
        String shopName = null;
        String shopLocation = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Shop WHERE Name = " + name + ";");
            while (rs.next()){
                shopName = rs.getString("Name");
                shopLocation = rs.getString("Location");
            }

            rs = stmt.executeQuery("SELECT * FROM Employee WHERE ShopName = " + name +";");
            while (rs.next()){
                int id = rs.getInt("IDNUM");
                String ename = rs.getString("NAME");
                String gender = rs.getString("GENDE");
                float salary = rs.getFloat("SALARY");
                String post = rs.getString("POST");
                employees.add(new Employee(id ,ename, gender, salary, post, name));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return new Shop(shopName, shopLocation, employees);
    }

    @Override
    public Collection<Shop> getShopByLocation(String location) {
        return null;
    }

    @Override
    public boolean updateShop(String name) {
        return false;
    }

    @Override
    public boolean updateShop(Shop shop) {
        return false;
    }

    @Override
    public boolean deleteShop(String name) {
        return false;
    }

    @Override
    public boolean deleteShop(Shop shop) {
        return false;
    }
}
