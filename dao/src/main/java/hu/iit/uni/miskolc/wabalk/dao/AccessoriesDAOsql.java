package hu.iit.uni.miskolc.wabalk.dao;

import hu.iit.uni.miskolc.wabalk.service.dao.AccessoriesDAO;
import hu.iit.uni.miskolc.webalk.core.model.Accessories;

import java.util.Collection;

public class AccessoriesDAOsql implements AccessoriesDAO {
    @Override
    public void createAccessories(Accessories accessories) {

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
    public boolean updateAccessories(String appellation, String brand) {
        return false;
    }

    @Override
    public boolean updateAccessoriesByBrand(String brand) {
        return false;
    }

    @Override
    public boolean deleteAccessories(String appellation) {
        return false;
    }

    @Override
    public boolean deleteAccessoriesByBrand(String brand) {
        return false;
    }
}
