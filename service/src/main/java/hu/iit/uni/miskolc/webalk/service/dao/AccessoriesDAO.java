package hu.iit.uni.miskolc.webalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.model.Accessories;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.*;

import java.util.Collection;

public interface AccessoriesDAO {

    void createAccessories(Accessories accessories) throws StorageNotAvailableException, StorageException, AlreadyExistingException, WrongFormatException;

    Collection<Accessories> getAccessoriesByAppellation(String appellation) throws StorageNotAvailableException, StorageException, NotFoundException, WrongFormatException;

    Collection<Accessories> getAccessoriesByBrand(String brand) throws StorageNotAvailableException, StorageException, NotFoundException, WrongFormatException;

    Collection<Accessories> getAllAccessories() throws StorageNotAvailableException, StorageException, NotFoundException, WrongFormatException;

    boolean updateAccessories(Accessories accessories) throws StorageNotAvailableException, StorageException, NotFoundException, AlreadyExistingException;

    boolean deleteAccessories(Accessories accessories) throws StorageNotAvailableException, StorageException, NotFoundException;

    boolean deleteAccessoriesByBrand(String brand) throws StorageNotAvailableException, StorageException, NotFoundException;
}
