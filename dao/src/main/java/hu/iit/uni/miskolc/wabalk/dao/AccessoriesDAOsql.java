package hu.iit.uni.miskolc.wabalk.dao;

import hu.iit.uni.miskolc.wabalk.service.dao.AccessoriesDAO;
import hu.iit.uni.miskolc.webalk.core.exceptions.AlreadyExistingException;
import hu.iit.uni.miskolc.webalk.core.exceptions.PersistanceException;
import hu.iit.uni.miskolc.webalk.core.exceptions.StorageException;
import hu.iit.uni.miskolc.webalk.core.exceptions.WrongDataTypeException;
import hu.iit.uni.miskolc.webalk.core.model.Accessories;

import java.sql.*;
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
    public Collection<Accessories> getAccessoriesByAppellation(String appellation) {
        return null;
    }

    @Override
    public Collection<Accessories> getAccessoriesByBrand(String brand) {
        return null;
    }

    @Override
    public boolean updateAccessories(Accessories accessories) {
        return false;
    }

    @Override
    public boolean deleteAccessories(Accessories accessories) {
        return false;
    }

    @Override
    public boolean deleteAccessoriesByBrand(String brand) {
        return false;
    }
}
