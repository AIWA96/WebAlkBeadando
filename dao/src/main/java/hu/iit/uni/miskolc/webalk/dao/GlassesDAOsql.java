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

    private String con;
    private Connection c;
    private Statement stmt;

    public GlassesDAOsql() {
        con = DataBase.getCon();
    }

    @Override
    public void createGlasses(Glasses glasses) throws AlreadyExistException, StorageException, PersistenceException {
        String sql = "INSERT INTO Glasses (Brand, Model, Price, AvailableAt, Gender, Sunglasses) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, glasses.getBrand());
            ps.setString(2, glasses.getModel());
            ps.setFloat(3, glasses.getPrice());
            ps.setString(4, glasses.getAvailableAt());
            ps.setString(5, glasses.getGender());
            ps.setInt(6, glasses.isSunglasses() ? 0 : 1);
            ps.executeUpdate();

            c.commit();
            c.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                throw new AlreadyExistException(e);
            }
            throw new StorageException();
        } catch (ClassNotFoundException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Collection<Glasses> getGlasses(String brand) throws StorageException, PersistenceException {
        String model;
        float price;
        String availableAt;
        String gender;
        boolean sunglasses;
        ArrayList<Glasses> glasses = new ArrayList<>();
        String sql = "SELECT * FROM Glasses WHERE  Brand = " + brand + ";";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                model = rs.getString("Model");
                price = rs.getFloat("Price");
                availableAt = rs.getString("AvailableAt");
                gender = rs.getString("Gender");
                sunglasses = rs.getInt("Sunglasses") > 0 ? true : false;
                glasses.add(new Glasses(brand, model, price, availableAt, gender, sunglasses));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException e) {
            throw new StorageException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        return glasses;
    }

    @Override
    public Glasses getGlasses(String brand, String model) throws StorageException, PersistenceException, NoArgumentException {
        float price = 0;
        String availableAt = null;
        String gender = null;
        boolean sunglasses = false;
        Glasses glasses = null;
        String sql = "SELECT * FROM Glasses WHERE  Brand = \'" + brand + "\' AND " + "Model = \'" + model + "\';";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);

            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
                price = rs.getFloat("Price");
                availableAt = rs.getString("AvailableAt");
                gender = rs.getString("Gender");
                sunglasses = rs.getInt("Sunglasses") > 0 ? true : false;
            }
            glasses = new Glasses(brand, model, price, availableAt, gender, sunglasses);
            rs.close();
            c.close();
        } catch (SQLException e) {
            throw new StorageException(e);
        } catch (InvalidPriceException | NoLocationSetException | NoGenderException | NoNameException e) {
            throw new NoArgumentException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        return glasses;
    }

    @Override
    public boolean updateGlasses(Glasses glasses) throws AlreadyExistException, StorageException, PersistenceException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);
            String sql = "UPDATE Glasses SET Price = ? WHERE Brand = ? AND Model = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setFloat(1, glasses.getPrice());
            ps.setString(2, glasses.getBrand());
            ps.setString(3, glasses.getModel());
            if (ps.executeUpdate() == 0) {
                throw new NotFoundException();
            }
            c.commit();
            c.close();
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
    public boolean deleteGlasses(String brand, String model) throws StorageException, PersistenceException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);
            String sql = "DELETE FROM Glasses WHERE Brand = ? AND Model = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, brand);
            ps.setString(2, model);
            if (ps.executeUpdate() == 0) {
                throw new NotFoundException();
            }
            c.commit();
            c.close();
        } catch (SQLException e) {
            throw new StorageException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        return true;
    }

    @Override
    public boolean deleteGlasses(Glasses glasses) throws PersistenceException, StorageException {
        return deleteGlasses(glasses.getBrand(), glasses.getModel());
    }
}
