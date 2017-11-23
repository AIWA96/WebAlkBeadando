package hu.iit.uni.miskolc.webalk.core.service;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Accessories;

import java.util.Collection;

public interface AccessoriesService {

    void createAccessories(Accessories accessories) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException, PersistanceException, WrongDataTypeException;

    Collection<Accessories> getAccessoriesByAppellation(String appellation) throws AlreadyExistingException, PersistanceException, StorageException, WrongDataTypeException;
    Collection<Accessories> getAccessoriesByBrand(String brand) throws AlreadyExistingException, PersistanceException, StorageException, WrongDataTypeException;

    boolean updateAccessories(Accessories accessories) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException, PersistanceException;

    boolean deleteAccessories(Accessories accessories) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException;
    boolean deleteAccessories(String brand) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException;
}
