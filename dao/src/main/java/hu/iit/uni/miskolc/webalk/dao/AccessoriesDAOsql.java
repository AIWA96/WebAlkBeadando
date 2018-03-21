package hu.iit.uni.miskolc.webalk.dao;

import hu.iit.uni.miskolc.webalk.core.model.Accessories;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.service.dao.AccessoriesDAO;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.AlreadyExistException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.NotFoundException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.StorageException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class AccessoriesDAOsql implements AccessoriesDAO {

    private String con;
    private Connection conn = null;

    public AccessoriesDAOsql() {
        DataBase.getInstance();
        con = DataBase.getConnection();
    }

    @Override
    public void createAccessories(Accessories accessories) throws AlreadyExistException, StorageException {
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

        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                throw new AlreadyExistException(e);
            }
            throw new StorageException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
    public Collection<Accessories> getAccessoriesByAppellation(String appellation) throws StorageException, PersistenceException {
        Collection<Accessories> accessories = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            String brand;
            float price;
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
        return accessories;
    }

    @Override
    public Collection<Accessories> getAccessoriesByBrand(String brand) throws StorageException, PersistenceException {
        Collection<Accessories> accessories = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            String appellation;
            float price;
            String sql = "SELECT * FROM Accessories WHERE Brand = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, brand);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                appellation = rs.getString("Appellation");
                price = rs.getFloat("Price");
                accessories.add(new Accessories(appellation, brand, price));
            }
            rs.close();
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
        return accessories;
    }

    @Override
    public Collection<Accessories> getAllAccessories() throws AlreadyExistException, StorageException, PersistenceException {
        Collection<Accessories> accessories = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            String appellation;
            String brand;
            float price;
            String sql = "SELECT * FROM Accessories";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                appellation = rs.getString("Appellation");
                brand = rs.getString("Brand");
                price = rs.getFloat("Price");
                accessories.add(new Accessories(appellation, brand, price));
            }
            rs.close();
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
            ps.close();
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
        }
        return true;
    }

    @Override
    public boolean deleteAccessories(Accessories accessories) throws StorageException, PersistenceException {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            String sql = "DELETE FROM Accessories WHERE Brand = ? AND Appellation = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accessories.getBrand());
            ps.setString(2, accessories.getAppellation());
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
    public boolean deleteAccessoriesByBrand(String brand) throws StorageException, PersistenceException {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            String sql = "DELETE FROM Accessories WHERE Brand = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, brand);
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
}
