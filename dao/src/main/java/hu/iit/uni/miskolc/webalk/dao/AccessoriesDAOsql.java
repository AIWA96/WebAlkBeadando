package hu.iit.uni.miskolc.webalk.dao;

import hu.iit.uni.miskolc.webalk.core.model.Accessories;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.service.dao.AccessoriesDAO;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.AlreadyExistException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.CreateDataBaseException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.NotFoundException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.StorageException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class AccessoriesDAOsql implements AccessoriesDAO {

    private DataBase dataBase;
    private String con;
    private Connection conn;
    private Statement stmt;

    public AccessoriesDAOsql() throws CreateDataBaseException {
        dataBase = new DataBase();
        con = DataBase.getCon();
    }

    @Override
    public void createAccessories(Accessories accessories) throws PersistenceException, AlreadyExistException, StorageException {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            String sql = "INSERT INTO Accessories (Appellation, Brand, Price) VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accessories.getAppellation());
            ps.setString(2, accessories.getBrand());
            ps.setFloat(3, accessories.getPrice());
            ps.executeUpdate();

            conn.commit();
            conn.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                throw new AlreadyExistException(e);
            }
            throw new StorageException(e);
        } catch (ClassNotFoundException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Collection<Accessories> getAccessoriesByAppellation(String appellation) throws StorageException, PersistenceException {
        String brand;
        float price;
        Collection<Accessories> accessories = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);

            String sql = "SELECT * FROM Accessories WHERE Appellation = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, appellation);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                brand = rs.getString("Brand");
                price = rs.getFloat("Price");
                accessories.add(new Accessories(appellation, brand, price));
            }
            rs.close();
            conn.close();
        } catch (SQLException e) {
            throw new StorageException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        return accessories;
    }

    @Override
    public Collection<Accessories> getAccessoriesByBrand(String brand) throws StorageException, PersistenceException {
        String appellation;
        float price;
        Collection<Accessories> accessories = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Accessories WHERE Brand = "
                    + brand + ";");
            while (rs.next()) {
                appellation = rs.getString("Appellation");
                price = rs.getFloat("Price");
                accessories.add(new Accessories(appellation, brand, price));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new StorageException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        return accessories;
    }

    @Override
    public Collection<Accessories> getAllAccessories() throws AlreadyExistException, StorageException, PersistenceException {
        String appellation;
        String brand;
        float price;
        Collection<Accessories> accessories = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Accessories");
            while (rs.next()) {
                appellation = rs.getString("Appellation");
                brand = rs.getString("Brand");
                price = rs.getFloat("Price");
                accessories.add(new Accessories(appellation, brand, price));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                throw new AlreadyExistException(e);
            }
            throw new StorageException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        return accessories;
    }

    @Override
    public boolean updateAccessories(Accessories accessories) throws AlreadyExistException, StorageException, PersistenceException {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);
            String sql = "UPDATE Accessories SET Price = ? WHERE Appellation = ? AND Brand = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, accessories.getPrice());
            ps.setString(2, accessories.getAppellation());
            ps.setString(3, accessories.getBrand());
            if (ps.executeUpdate() == 0) {
                throw new NotFoundException();
            }
            conn.commit();
            conn.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new AlreadyExistException(e);
        } catch (SQLException e) {
            throw new StorageException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        return true;
    }

    @Override
    public boolean deleteAccessories(Accessories accessories) throws StorageException, PersistenceException {
        String sql = "DELETE FROM Accessories WHERE Brand = ? AND Appellation = ?";
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accessories.getBrand());
            ps.setString(2, accessories.getAppellation());
            if (ps.executeUpdate() == 0) {
                throw new NotFoundException();
            }
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            throw new StorageException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        return true;
    }

    @Override
    public boolean deleteAccessoriesByBrand(String brand) throws StorageException, PersistenceException {
        String sql = "DELETE FROM Accessories WHERE Brand = ?";
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, brand);
            if (ps.executeUpdate() == 0) {
                throw new NotFoundException();
            }
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            throw new StorageException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        return true;
    }
}
