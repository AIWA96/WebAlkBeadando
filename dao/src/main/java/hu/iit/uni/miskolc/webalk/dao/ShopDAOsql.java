package hu.iit.uni.miskolc.webalk.dao;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Employee;
import hu.iit.uni.miskolc.webalk.core.model.Shop;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.service.dao.EmployeeDAO;
import hu.iit.uni.miskolc.webalk.service.dao.ShopDAO;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.AlreadyExistException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.NoArgumentException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.NotFoundException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.StorageException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShopDAOsql implements ShopDAO {

    private String con;
    private DataBase db;
    private Connection conn = null;

    public ShopDAOsql() {
        db = DataBase.getInstance();
        con = db.getConnection();
    }

    @Override
    public void createShop(Shop shop) throws AlreadyExistException, StorageException, PersistenceException {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            String sql = "INSERT INTO Shop (Name, Location) VALUES (?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, shop.getName());
            ps.setString(2, shop.getLocation());
            ps.executeUpdate();
            conn.commit();
            ps.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                throw new AlreadyExistException(e);
            }
            throw new StorageException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    @Override
    public Shop getShopByName(String name) throws StorageException, PersistenceException, NoArgumentException {
        Collection<Employee> employees = new ArrayList<>();
        String shopLocation;
        String shopsql = "SELECT * FROM Shop WHERE Name = ?;";
        String employeesql = "SELECT * FROM Employee WHERE ShopName = ?;";
        Shop shop;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(shopsql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                shopLocation = rs.getString("Location");
            } else {
                throw new NotFoundException();
            }

            ps = conn.prepareStatement(employeesql);
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
            shop = new Shop(name, shopLocation, employees);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new StorageException(e);
        } catch (NoEmployeeException | NoNameException | NoLocationException | NoGenderException | NoPostException | InvalidSalaryException e) {
            throw new NoArgumentException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
        return shop;
    }

    @Override
    public Collection<Shop> getShopByLocation(String location) throws StorageException, NoArgumentException, PersistenceException {
        List<String> shopNames = new ArrayList<>();
        ArrayList<Shop> shops = new ArrayList<>();
        String shopName;
        String shopsql = "SELECT * FROM Shop WHERE Location = ?;";
        String employeesql = "SELECT * FROM Employee WHERE ShopName = ?;";

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(shopsql);
            ps.setString(1, location);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                shopName = rs.getString("Name");
                shopNames.add(shopName);
            }

            ps = conn.prepareStatement(employeesql);
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
        } catch (SQLException e) {
            throw new StorageException(e);
        } catch (NoEmployeeException | NoNameException | NoLocationException | NoGenderException | NoPostException | InvalidSalaryException e) {
            throw new NoArgumentException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
        return shops;
    }

    @Override
    public Collection<Shop> getAllShops() throws StorageException, NoArgumentException, PersistenceException {
        List<String> shopNames = new ArrayList<>();
        List<String> shopLocations = new ArrayList<>();
        ArrayList<Shop> shops = new ArrayList<>();
        String shopName;
        String shopLocation;
        String shopsql = "SELECT * FROM Shop;";
        String employeesql = "SELECT * FROM Employee WHERE ShopName = ?;";

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(shopsql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                shopName = rs.getString("Name");
                shopLocation = rs.getString("Location");
                shopNames.add(shopName);
                shopLocations.add(shopLocation);
            }

            ps = conn.prepareStatement(employeesql);
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
                shops.add(new Shop(shopNames.get(i), shopLocations.get(i), employees));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new StorageException(e);
        } catch (NoEmployeeException | NoNameException | NoLocationException | NoGenderException | NoPostException | InvalidSalaryException e) {
            throw new NoArgumentException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
        return shops;
    }

    @Override
    public boolean updateShop(Shop shop) throws AlreadyExistException, StorageException, PersistenceException {
        String select = "SELECT NAME FROM Shop WHERE LOCATION = ?";
        String sql = "UPDATE Shop SET NAME = ? WHERE LOCATION = ?";
        String oldName = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(select);
            ps.setString(1, shop.getLocation());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                oldName = rs.getString("Name");
            }

            ps = conn.prepareStatement(sql);
            ps.setString(1, shop.getName());
            ps.setString(2, shop.getLocation());
            if (ps.executeUpdate() == 0) {
                throw new NotFoundException();
            }

            conn.commit();
            ps.close();
            rs.close();
            EmployeeDAO employeeDAO = new EmployeeDAOsql();
            if (!employeeDAO.updateEmployeeWorkPlaceName(shop.getName(), oldName)) {
                throw new PersistenceException("Failure!");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new AlreadyExistException(e);
        } catch (SQLException e) {
            throw new StorageException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
            sql = null;
        }
        return true;
    }

    @Override
    public boolean deleteShop(String name) throws StorageException, PersistenceException {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            String sql = "DELETE FROM Shop WHERE Name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            if (ps.executeUpdate() == 0) {
                throw new NotFoundException();
            }
            conn.commit();
            ps.close();
        } catch (SQLException e) {
            throw new StorageException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
        return true;
    }

    @Override
    public boolean deleteShop(Shop shop) throws PersistenceException, StorageException {
        return deleteShop(shop.getName());
    }
}
