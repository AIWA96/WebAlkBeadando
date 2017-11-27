package hu.iit.uni.miskolc.webalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Accessories;

import java.util.Collection;

public interface AccessoriesDAO {
    void createAccessories(Accessories accessories) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistenceException;

    Collection<Accessories> getAccessoriesByAppellation(String appellation) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistenceException;
    Collection<Accessories> getAccessoriesByBrand(String brand) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistenceException;
    Collection<Accessories> getAllAccessories() throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistenceException;

    boolean updateAccessories(Accessories accessories) throws ClassNotFoundException, NotFoundException, AlreadyExistingException, StorageException, PersistenceException;

    boolean deleteAccessories(Accessories accessories) throws ClassNotFoundException, StorageException, NotFoundException, AlreadyExistingException;
    boolean deleteAccessoriesByBrand(String brand) throws ClassNotFoundException, NotFoundException, AlreadyExistingException, StorageException;
}
