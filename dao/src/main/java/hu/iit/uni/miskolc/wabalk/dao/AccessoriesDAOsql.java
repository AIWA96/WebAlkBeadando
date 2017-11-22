package hu.iit.uni.miskolc.wabalk.dao;

import hu.iit.uni.miskolc.wabalk.service.dao.AccessoriesDAO;
import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Accessories;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class AccessoriesDAOsql implements AccessoriesDAO {

    private static String OS = null;
    private String con;
    private Connection c;
    private Statement stmt;

    public AccessoriesDAOsql() {
        con = ConnectionString.getConnectionString();
    }

    @Override
    public void createAccessories(Accessories accessories) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO Accessories (Appellation, Brand, Price) VALUES ("
                    + accessories.getAppellation() + ", " + accessories.getBrand() +
                    accessories.getPrice() + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new AlreadyExistingException();
        } catch (SQLDataException e) {
            throw new WrongDataTypeException();
        } catch (SQLException e) {
            throw new StorageException();
        } catch (Exception e) {
            throw new PersistanceException();
        }
    }

    @Override
    public Collection<Accessories> getAccessoriesByAppellation(String appellation) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException {
        String brand;
        float price;
        ArrayList<Accessories> accessories = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Accessories WHERE Appellation = "
                    + appellation + ";");
            while (rs.next()) {
                brand = rs.getString("Brand");
                price = rs.getFloat("Price");
                accessories.add(new Accessories(appellation, brand, price));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new AlreadyExistingException();
        } catch (SQLDataException e) {
            throw new WrongDataTypeException();
        } catch (SQLException e) {
            throw new StorageException();
        } catch (Exception e) {
            throw new PersistanceException();
        }
        return accessories;
    }

    @Override
    public Collection<Accessories> getAccessoriesByBrand(String brand) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException {
        String appellation;
        float price;
        ArrayList<Accessories> accessories = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Accessories WHERE Brand = "
                    + brand + ";");
            while (rs.next()) {
                appellation = rs.getString("Appellation");
                price = rs.getFloat("Price");
                accessories.add(new Accessories(appellation, brand, price));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new AlreadyExistingException();
        } catch (SQLDataException e) {
            throw new WrongDataTypeException();
        } catch (SQLException e) {
            throw new StorageException();
        } catch (Exception e) {
            throw new PersistanceException();
        }
        return accessories;
    }

    @Override
    public boolean updateAccessories(Accessories accessories) throws ClassNotFoundException, NotFoundException, AlreadyExistingException, StorageException, PersistanceException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);
            String sql = "UPDATE Accessories SET Price = ? WHERE Appellation = ? AND Brand = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setFloat(1, accessories.getPrice());
            ps.setString(2, accessories.getAppellation());
            ps.setString(3, accessories.getBrand());
            if (ps.executeUpdate() == 0) {
                throw new NotFoundException();
            }
            c.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new AlreadyExistingException();
        } catch (SQLException e) {
            throw new StorageException();
        } catch (Exception e) {
            throw new PersistanceException();
        }
        return true;
    }

    @Override
    public boolean deleteAccessories(Accessories accessories) throws ClassNotFoundException, StorageException, NotFoundException, AlreadyExistingException {
        return deleteAccessoriesByBrand(accessories.getBrand());
    }

    @Override
    public boolean deleteAccessoriesByBrand(String brand) throws ClassNotFoundException, NotFoundException, AlreadyExistingException, StorageException {
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);
            String sql = "DELETE FROM Accessories WHERE Brand = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, brand);
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
}
