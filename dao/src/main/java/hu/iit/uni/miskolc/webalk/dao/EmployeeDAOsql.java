package hu.iit.uni.miskolc.webalk.dao;

import hu.iit.uni.miskolc.webalk.core.exceptions.InvalidSalaryException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoGenderException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoPostException;
import hu.iit.uni.miskolc.webalk.core.model.Employee;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.service.dao.EmployeeDAO;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.AlreadyExistException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.NoArgumentException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.NotFoundException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.StorageException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class EmployeeDAOsql implements EmployeeDAO {

    private String con;
    private Connection c;
    private Statement stmt;

    public EmployeeDAOsql() {
        con = DataBase.getCon();
    }

    @Override
    public void createEmployee(Employee employee) throws AlreadyExistException, StorageException, PersistenceException {
        String sql = "INSERT INTO Employee (IDNUM, NAME, GENDER, SALARY, POST, ShopName) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, employee.getIdNum());
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getGender());
            ps.setFloat(4, employee.getSalary());
            ps.setString(5, employee.getPost());
            ps.setString(6, employee.getShopName());
            ps.executeUpdate();

            c.commit();
            c.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                throw new AlreadyExistException();
            }
            throw new StorageException();
        } catch (ClassNotFoundException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public Employee getEmployee(int id) throws StorageException, PersistenceException, NoArgumentException {
        String ename = null;
        String gender = null;
        float salary = 0;
        String post = null;
        String shopName = null;
        Employee employee;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee WHERE IDNUM = " + id + ";");
            if (rs.next()) {
                ename = rs.getString("NAME");
                gender = rs.getString("GENDER");
                salary = rs.getFloat("SALARY");
                post = rs.getString("POST");
                shopName = rs.getString("ShopName");
            }
            employee = new Employee(id, ename, gender, salary, post, shopName);
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException e) {
            throw new StorageException();
        } catch (NoNameException | NoPostException | InvalidSalaryException | NoGenderException e) {
            throw new NoArgumentException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> getAllEmployee() throws StorageException, PersistenceException, NoArgumentException {
        int id;
        String name;
        String gender;
        String post;
        float salary;
        String shopName;
        Collection<Employee> employees = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);

            String sql = "SELECT * FROM Employee";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("IDNUM");
                name = rs.getString("Name");
                gender = rs.getString("Gender");
                post = rs.getString("Post");
                salary = rs.getFloat("Salary");
                shopName = rs.getString("ShopName");
                employees.add(new Employee(id, name, gender, salary, post, shopName));
            }
            rs.close();
            c.close();
        } catch (SQLException e) {
            throw new StorageException();
        } catch (NoNameException | NoPostException | InvalidSalaryException | NoGenderException e) {
            throw new NoArgumentException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
        return employees;
    }

    @Override
    public boolean updateEmployee(Employee employee) throws AlreadyExistException, StorageException, PersistenceException {
        String sql = "UPDATE Employee SET SALARY = ?, POST = ? WHERE ShopName = ?";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setFloat(1, employee.getSalary());
            ps.setString(2, employee.getPost());
            ps.setString(3, employee.getShopName());
            if (ps.executeUpdate() == 0) {
                throw new NotFoundException();
            }
            c.commit();
            c.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new AlreadyExistException();
        } catch (SQLException e) {
            throw new StorageException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
        return true;
    }

    @Override
    public boolean deleteEmployee(int id) throws StorageException, PersistenceException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);
            String sql = "DELETE FROM Employee WHERE IDNUM = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() == 0) {
                throw new NotFoundException();
            }
            c.commit();
            c.close();
        } catch (SQLException e) {
            throw new StorageException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
        return true;
    }

    @Override
    public boolean deleteEmployee(Employee employee) throws PersistenceException, StorageException {
        return deleteEmployee(employee.getIdNum());
    }
}
