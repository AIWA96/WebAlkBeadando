package hu.iit.uni.miskolc.webalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.model.Accessories;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.AlreadyExistingException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.NotFoundException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.StorageException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.StorageNotAvailableException;

import java.util.Collection;

public interface AccessoriesDAO {

    void createAccessories(Accessories accessories) throws StorageNotAvailableException, StorageException, AlreadyExistingException, PersistenceException;

    Collection<Accessories> getAccessoriesByAppellation(String appellation) throws StorageNotAvailableException, StorageException, NotFoundException, PersistenceException;

    Collection<Accessories> getAccessoriesByBrand(String brand) throws StorageNotAvailableException, StorageException, NotFoundException, PersistenceException;

    Collection<Accessories> getAllAccessories() throws StorageNotAvailableException, StorageException, NotFoundException, AlreadyExistingException, PersistenceException;

    boolean updateAccessories(Accessories accessories) throws StorageNotAvailableException, StorageException, NotFoundException, AlreadyExistingException, PersistenceException;

    boolean deleteAccessories(Accessories accessories) throws StorageNotAvailableException, StorageException, NotFoundException, PersistenceException;

    boolean deleteAccessoriesByBrand(String brand) throws StorageNotAvailableException, StorageException, NotFoundException, PersistenceException;
}
