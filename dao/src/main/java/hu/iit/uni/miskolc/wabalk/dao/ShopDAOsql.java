package hu.iit.uni.miskolc.wabalk.dao;

import hu.iit.uni.miskolc.wabalk.service.dao.ShopDAO;
import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Employee;
import hu.iit.uni.miskolc.webalk.core.model.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShopDAOsql implements ShopDAO {

    private static String OS = null;
    private String con;
    private Connection c;
    private Statement stmt;

    public ShopDAOsql() {
        con = ConnectionString.getConnectionString();
    }

    @Override
    public void createShop(Shop shop) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO Shop (Name, Location) VALUES ("
                    + shop.getName() + ", " + shop.getLocation() + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        }catch (SQLIntegrityConstraintViolationException e){
            throw new AlreadyExistingException();
        }catch (SQLDataException e){
            throw new WrongDataTypeException();
        }catch (SQLException e){
            throw new StorageException();
        }catch ( Exception e ) {
            throw new PersistanceException();
        }
    }

    @Override
    public Shop getShopByName(String name) throws NoEmployeeException, NoNameException, NoLocationException, AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException {
        Collection<Employee> employees = new ArrayList<>();
        String shopName = null;
        String shopLocation = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);

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
                String gender = rs.getString("GENDER");
                float salary = rs.getFloat("SALARY");
                String post = rs.getString("POST");
                employees.add(new Employee(id ,ename, gender, salary, post, name));
            }
            rs.close();
            stmt.close();
            c.close();
        }catch (SQLIntegrityConstraintViolationException e){
            throw new AlreadyExistingException();
        }catch (SQLDataException e){
            throw new WrongDataTypeException();
        }catch (SQLException e){
            throw new StorageException();
        }catch ( Exception e ) {
            throw new PersistanceException();
        }
        return new Shop(shopName, shopLocation, employees);
    }

    @Override
    public Collection<Shop> getShopByLocation(String location) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException {
        Collection<Employee> employees = new ArrayList<>();
        List<String> shopNames = new ArrayList<>();
        ArrayList<Shop> shops = new ArrayList<>();
        String shopName;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Shop WHERE Location = " + location + ";");
            while (rs.next()){
                shopName = rs.getString("Name");
                shopNames.add(shopName);
            }

            for (int i = 0; i <= shopNames.size(); i++){
                rs = stmt.executeQuery("SELECT * FROM Employee WHERE ShopName = " + shopNames.get(i) + ";");
                while (rs.next()){
                    int id = rs.getInt("IDNUM");
                    String ename = rs.getString("NAME");
                    String gender = rs.getString("GENDER");
                    float salary = rs.getFloat("SALARY");
                    String post = rs.getString("POST");
                    employees.add(new Employee(id ,ename, gender, salary, post, shopNames.get(i)));
                }
                shops.add(new Shop(shopNames.get(i), location, employees));
                employees.clear();
            }
            rs.close();
            stmt.close();
            c.close();
        }catch (SQLIntegrityConstraintViolationException e){
            throw new AlreadyExistingException();
        }catch (SQLDataException e){
            throw new WrongDataTypeException();
        }catch (SQLException e){
            throw new StorageException();
        }catch ( Exception e ) {
            throw new PersistanceException();
        }
        return shops;
    }

    @Override
    public boolean updateShop(Shop shop) throws NotFoundException, StorageNotAvailableException, AlreadyExistingException, StorageException, ClassNotFoundException {
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);
            String sql = "UPDATE Shop SET NAME = ? WHERE LOCATION = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1,shop.getName());
            ps.setString(2, shop.getLocation());
            if (ps.executeUpdate() == 0){
                throw new NotFoundException();
            }
            c.close();
        } catch (SQLIntegrityConstraintViolationException e){
            throw new AlreadyExistingException();
        }catch ( SQLException e ) {
            throw new StorageException();
        }
        return true;
    }

    @Override
    public boolean deleteShop(String name) throws AlreadyExistingException, StorageException, StorageNotAvailableException, ClassNotFoundException, NotFoundException {
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);
            String sql = "DELETE FROM Shop WHERE Name = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1,name);
            if (ps.executeUpdate() == 0){
                throw new NotFoundException();
            }
            c.close();
        } catch (SQLIntegrityConstraintViolationException e){
            throw new AlreadyExistingException();
        }catch ( SQLException e ) {
            throw new StorageException();
        }
        return true;
    }

    @Override
    public boolean deleteShop(Shop shop) throws AlreadyExistingException, StorageNotAvailableException, StorageException, ClassNotFoundException, NotFoundException {
        return deleteShop(shop.getName());
    }
}
