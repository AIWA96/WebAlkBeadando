package hu.iit.uni.miskolc.webalk.dao;

import hu.iit.uni.miskolc.webalk.core.model.Accessories;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.service.dao.AccessoriesDAO;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.AlreadyExistingException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.NotFoundException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.StorageException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class AccessoriesDAOsql implements AccessoriesDAO {

    private String con = "jdbc:sqlite:./database/glassShop.db";
    private Connection conn;
    private Statement stmt;

    public AccessoriesDAOsql() {
    }

    @Override
    public void createAccessories(Accessories accessories) throws PersistenceException, AlreadyExistingException, StorageException {
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
                throw new AlreadyExistingException();
            }
            throw new StorageException();
        } catch (ClassNotFoundException e) {
            throw new PersistenceException();
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
            throw new StorageException();
        } catch (Exception e) {
            throw new PersistenceException();
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
            throw new StorageException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
        return accessories;
    }

    @Override
    public Collection<Accessories> getAllAccessories() throws AlreadyExistingException, StorageException, PersistenceException {
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
                throw new AlreadyExistingException();
            }
            throw new StorageException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
        return accessories;
    }

    @Override
    public boolean updateAccessories(Accessories accessories) throws AlreadyExistingException, StorageException, PersistenceException {
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
            throw new AlreadyExistingException();
        } catch (SQLException e) {
            throw new StorageException();
        } catch (Exception e) {
            throw new PersistenceException();
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
            throw new StorageException();
        } catch (Exception e){
            throw new PersistenceException();
        }
        return true;
    }

    @Override
    public boolean deleteAccessoriesByBrand(String brand) throws NotFoundException, StorageException, PersistenceException {
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
            throw new StorageException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
        return true;
    }
}
