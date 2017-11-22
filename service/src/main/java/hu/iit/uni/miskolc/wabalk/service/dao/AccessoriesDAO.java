package hu.iit.uni.miskolc.wabalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.exceptions.AlreadyExistingException;
import hu.iit.uni.miskolc.webalk.core.exceptions.PersistanceException;
import hu.iit.uni.miskolc.webalk.core.exceptions.StorageException;
import hu.iit.uni.miskolc.webalk.core.exceptions.WrongDataTypeException;
import hu.iit.uni.miskolc.webalk.core.model.Accessories;

import java.util.Collection;

public interface AccessoriesDAO {
    void createAccessories(Accessories accessories) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException;

    Collection<Accessories> getAccessoriesByAppellation(String appellation);
    Collection<Accessories> getAccessoriesByBrand(String brand);

    boolean updateAccessories(Accessories accessories);

    boolean deleteAccessories(Accessories accessories);
    boolean deleteAccessoriesByBrand(String brand);
}
