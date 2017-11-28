package hu.iit.uni.miskolc.webalk.core.service;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Accessories;

import java.util.Collection;

public interface AccessoriesService {

    /**
     * @param accessories
     * @throws AlreadyExistingException
     * @throws StorageNotAvailableException
     * @throws StorageException
     * @throws NotFoundException
     * @throws NoNameException
     * @throws PersistenceException
     * @throws WrongDataTypeException
     */
    void createAccessories(Accessories accessories) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException, PersistenceException, WrongDataTypeException;

    Collection<Accessories> getAccessoriesByAppellation(String appellation) throws AlreadyExistingException, PersistenceException, StorageException, WrongDataTypeException;

    Collection<Accessories> getAccessoriesByBrand(String brand) throws AlreadyExistingException, PersistenceException, StorageException, WrongDataTypeException;

    Collection<Accessories> getAllAccessories() throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistenceException;

    boolean updateAccessories(Accessories accessories) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException, PersistenceException;

    boolean deleteAccessories(Accessories accessories) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException;

    boolean deleteAccessoriesByBrand(String brand) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException;
}
