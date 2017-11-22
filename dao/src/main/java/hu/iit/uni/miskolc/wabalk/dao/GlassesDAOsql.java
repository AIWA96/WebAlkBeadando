package hu.iit.uni.miskolc.wabalk.dao;

import hu.iit.uni.miskolc.wabalk.service.dao.GlassesDAO;
import hu.iit.uni.miskolc.webalk.core.exceptions.AlreadyExistingException;
import hu.iit.uni.miskolc.webalk.core.exceptions.PersistanceException;
import hu.iit.uni.miskolc.webalk.core.exceptions.StorageException;
import hu.iit.uni.miskolc.webalk.core.exceptions.WrongDataTypeException;
import hu.iit.uni.miskolc.webalk.core.model.Glasses;

import java.sql.*;
import java.util.Collection;

public class GlassesDAOsql implements GlassesDAO {

    private static String OS = null;
    private String con;
    private Connection c;
    private Statement stmt;

    public GlassesDAOsql() {
        con = ConnectionString.getConnectionString();
    }

    @Override
    public void createGlasses(Glasses glasses) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(con);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO Glasses (Brand, Model, Price, AvailableAt, Gender, Sunglasses " +
                    "VALUES (" + glasses.getBrand() + ", " + glasses.getModel() + ", " + glasses.getPrice()
                    + ", " + glasses.getAvailableAt() + ", " + glasses.getGender() + ", "
                    + glasses.isSunglasses() + ");";
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
    public Collection<Glasses> getGlasses(String brand) {
        return null;
    }

    @Override
    public Glasses getGlasses(String brand, String model) {
        return null;
    }

    @Override
    public boolean updateGlasses(Glasses glasses) {
        return false;
    }

    @Override
    public boolean deleteGlasses(String brand) {
        return false;
    }

    @Override
    public boolean deleteGlasses(Glasses glasses) {
        return false;
    }
}
