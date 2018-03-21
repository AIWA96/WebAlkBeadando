package hu.iit.uni.miskolc.webalk.dao;

import hu.iit.uni.miskolc.webalk.core.exceptions.InvalidPriceException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoGenderException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoLocationSetException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;
import hu.iit.uni.miskolc.webalk.core.model.Glasses;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.service.dao.GlassesDAO;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.AlreadyExistException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.NoArgumentException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.NotFoundException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.StorageException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class GlassesDAOsql implements GlassesDAO {

    private Connection conn = null;
    private String con;

    public GlassesDAOsql() {
        con = DataBase.getConnection();
    }

    @Override
    public void createGlasses(Glasses glasses) throws AlreadyExistException, StorageException, PersistenceException {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            String sql = "INSERT INTO Glasses (Brand, Model, Price, AvailableAt, Gender, Sunglasses) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, glasses.getBrand());
            ps.setString(2, glasses.getModel());
            ps.setFloat(3, glasses.getPrice());
            ps.setString(4, glasses.getAvailableAt());
            ps.setString(5, glasses.getGender());
            ps.setInt(6, glasses.isSunglasses() ? 0 : 1);
            ps.executeUpdate();

            conn.commit();
            ps.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                throw new AlreadyExistException(e);
            }
            throw new StorageException(e);
        } catch (ClassNotFoundException e) {
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
    public Collection<Glasses> getGlasses(String brand) throws StorageException, PersistenceException {
        Collection<Glasses> glasses = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            String model;
            float price;
            String availableAt;
            String gender;
            boolean sunglasses;
            String sql = "SELECT * FROM Glasses WHERE Brand = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, brand);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model = rs.getString("Model");
                price = rs.getFloat("Price");
                availableAt = rs.getString("AvailableAt");
                gender = rs.getString("Gender");
                sunglasses = rs.getInt("Sunglasses") > 0;
                glasses.add(new Glasses(brand, model, price, availableAt, gender, sunglasses));
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
        return glasses;
    }

    @Override
    public Glasses getGlasses(String brand, String model) throws StorageException, PersistenceException, NoArgumentException {
        Glasses glasses;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            float price = 0;
            String availableAt = null;
            String gender = null;
            boolean sunglasses = false;
            String sql = "SELECT * FROM Glasses WHERE  Brand = ? AND Model = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, brand);
            ps.setString(2, model);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                price = rs.getFloat("Price");
                availableAt = rs.getString("AvailableAt");
                gender = rs.getString("Gender");
                sunglasses = rs.getInt("Sunglasses") > 0;
            }
            glasses = new Glasses(brand, model, price, availableAt, gender, sunglasses);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new StorageException(e);
        } catch (InvalidPriceException | NoLocationSetException | NoGenderException | NoNameException e) {
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
        return glasses;
    }

    @Override
    public boolean updateGlasses(Glasses glasses) throws AlreadyExistException, StorageException, PersistenceException {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            String sql = "UPDATE Glasses SET Price = ? WHERE Brand = ? AND Model = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, glasses.getPrice());
            ps.setString(2, glasses.getBrand());
            ps.setString(3, glasses.getModel());
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
    public boolean deleteGlasses(String brand, String model) throws StorageException, PersistenceException {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(con);
            conn.setAutoCommit(false);

            String sql = "DELETE FROM Glasses WHERE Brand = ? AND Model = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, brand);
            ps.setString(2, model);
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
    public boolean deleteGlasses(Glasses glasses) throws PersistenceException, StorageException {
        return deleteGlasses(glasses.getBrand(), glasses.getModel());
    }
}
