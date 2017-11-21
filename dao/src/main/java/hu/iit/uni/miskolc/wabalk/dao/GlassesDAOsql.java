package hu.iit.uni.miskolc.wabalk.dao;

import hu.iit.uni.miskolc.wabalk.service.dao.GlassesDAO;
import hu.iit.uni.miskolc.webalk.core.model.Glasses;

import java.util.Collection;

public class GlassesDAOsql implements GlassesDAO {
    @Override
    public void createGlasses(Glasses glasses) {

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
    public boolean updateGlasses(String brand, String model) {
        return false;
    }

    @Override
    public boolean updateGlasses(String brand) {
        return false;
    }

    @Override
    public boolean deleteGlasses(String brand, String model) {
        return false;
    }

    @Override
    public boolean deleteGlasses(String model) {
        return false;
    }
}
