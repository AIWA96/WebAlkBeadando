package hu.iit.uni.miskolc.webalk.dao;

import hu.iit.uni.miskolc.webalk.service.dao.ShopDAO;
import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Employee;
import hu.iit.uni.miskolc.webalk.core.model.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShopDAOsql implements ShopDAO {

    private String con;
    private Connection c;

    public ShopDAOsql() {
        con = ConnectionString.getConnectionString();
    }

    @Override
    public void createShop(Shop shop) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistenceException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);

            String sql = "INSERT INTO Shop (Name, Location) VALUES (?, ?);";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, shop.getName());
            ps.setString(2, shop.getLocation());
            ps.executeUpdate();
            c.commit();
            c.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new AlreadyExistingException();
        } catch (SQLDataException e) {
            throw new WrongDataTypeException();
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                throw new AlreadyExistingException();
            } else {
                throw new StorageException();
            }
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    @Override
    public Shop getShopByName(String name) throws NoEmployeeException, NoNameException, NoLocationException, AlreadyExistingException, WrongDataTypeException, StorageException, PersistenceException {
        Collection<Employee> employees = new ArrayList<>();
        String shopLocation;
        String shopsql = "SELECT * FROM Shop WHERE Name = ?;";
        String employeesql = "SELECT * FROM Employee WHERE ShopName = ?;";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);

            PreparedStatement ps = c.prepareStatement(shopsql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                shopLocation = rs.getString("Location");
            } else {
                throw new NotFoundException();
            }

            ps = c.prepareStatement(employeesql);
            ps.setString(1, name);
            ResultSet rs1 = ps.executeQuery();
            while (rs1.next()) {
                int id = rs1.getInt("IDNUM");
                String ename = rs1.getString("NAME");
                String gender = rs1.getString("GENDER");
                float salary = rs1.getFloat("SALARY");
                String post = rs1.getString("POST");
                employees.add(new Employee(id, ename, gender, salary, post, name));
            }
            rs.close();
            c.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new AlreadyExistingException();
        } catch (SQLDataException e) {
            throw new WrongDataTypeException();
        } catch (SQLException e) {
            throw new StorageException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
        return new Shop(name, shopLocation, employees);
    }

    @Override
    public Collection<Shop> getShopByLocation(String location) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistenceException {
        List<String> shopNames = new ArrayList<>();
        ArrayList<Shop> shops = new ArrayList<>();
        String shopName;
        String shopsql = "SELECT * FROM Shop WHERE Location = ?;";
        String employeesql = "SELECT * FROM Employee WHERE ShopName = ?;";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);

            PreparedStatement ps = c.prepareStatement(shopsql);
            ps.setString(1, location);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                shopName = rs.getString("Name");
                shopNames.add(shopName);
            }

            ps = c.prepareStatement(employeesql);
            for (int i = 0; i < shopNames.size(); i++) {
                Collection<Employee> employees = new ArrayList<>();
                ps.setString(1, shopNames.get(i));
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("IDNUM");
                    String ename = rs.getString("NAME");
                    String gender = rs.getString("GENDER");
                    float salary = rs.getFloat("SALARY");
                    String post = rs.getString("POST");
                    employees.add(new Employee(id, ename, gender, salary, post, shopNames.get(i)));
                }
                shops.add(new Shop(shopNames.get(i), location, employees));
            }
            rs.close();
            ps.close();
            c.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new AlreadyExistingException();
        } catch (SQLDataException e) {
            throw new WrongDataTypeException();
        } catch (SQLException e) {
            throw new StorageException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
        return shops;
    }

    @Override
    public Collection<Shop> getAllShops() throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistenceException {
        List<String> shopNames = new ArrayList<>();
        List<String> shopLocations = new ArrayList<>();
        ArrayList<Shop> shops = new ArrayList<>();
        String shopName;
        String shopLocation = null;
        String shopsql = "SELECT * FROM Shop;";
        String employeesql = "SELECT * FROM Employee WHERE ShopName = ?;";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);

            PreparedStatement ps = c.prepareStatement(shopsql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                shopName = rs.getString("Name");
                shopLocation = rs.getString("Location");
                shopNames.add(shopName);
                shopLocations.add(shopLocation);
            }

            ps = c.prepareStatement(employeesql);
            for (int i = 0; i < shopNames.size(); i++) {
                Collection<Employee> employees = new ArrayList<>();
                ps.setString(1, shopNames.get(i));
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("IDNUM");
                    String ename = rs.getString("NAME");
                    String gender = rs.getString("GENDER");
                    float salary = rs.getFloat("SALARY");
                    String post = rs.getString("POST");
                    employees.add(new Employee(id, ename, gender, salary, post, shopNames.get(i)));
                }
                if (employees.isEmpty()) {
                    continue;
                }
                shops.add(new Shop(shopNames.get(i), shopLocation, employees));
            }
            rs.close();
            ps.close();
            c.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new AlreadyExistingException();
        } catch (SQLDataException e) {
            throw new WrongDataTypeException();
        } catch (SQLException e) {
            throw new StorageException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
        return shops;
    }

    @Override
    public boolean updateShop(Shop shop) throws NotFoundException, StorageNotAvailableException, AlreadyExistingException, StorageException, ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);
            String sql = "UPDATE Shop SET NAME = ? WHERE LOCATION = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, shop.getName());
            ps.setString(2, shop.getLocation());
            if (ps.executeUpdate() == 0) {
                throw new NotFoundException();
            }
            c.commit();
            ps.close();
            c.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new AlreadyExistingException();
        } catch (SQLException e) {
            throw new StorageException();
        }
        return true;
    }

    @Override
    public boolean deleteShop(String name) throws AlreadyExistingException, StorageException, StorageNotAvailableException, ClassNotFoundException, NotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);
            String sql = "DELETE FROM Shop WHERE Name = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, name);
            if (ps.executeUpdate() == 0) {
                throw new NotFoundException();
            }
            c.commit();
            c.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new AlreadyExistingException();
        } catch (SQLException e) {
            throw new StorageException();
        }
        return true;
    }

    @Override
    public boolean deleteShop(Shop shop) throws AlreadyExistingException, StorageNotAvailableException, StorageException, ClassNotFoundException, NotFoundException {
        return deleteShop(shop.getName());
    }
}
