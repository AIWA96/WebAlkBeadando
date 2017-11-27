package hu.iit.uni.miskolc.webalk.core.service;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Glasses;

import java.util.Collection;

public interface GlassesService {

    /**
     * @param glasses
     * @throws AlreadyExistingException
     * @throws StorageNotAvailableException
     * @throws StorageException
     * @throws NotFoundException
     * @throws NoNameException
     * @throws PersistenceException
     * @throws WrongDataTypeException
     */
    void createGlass(Glasses glasses) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException, PersistenceException, WrongDataTypeException;

    Collection<Glasses> getGlasses(String brand) throws AlreadyExistingException, PersistenceException, StorageException, WrongDataTypeException;

    Glasses getGlasses(String brand, String model) throws NoLocationSetException, InvalidPriceException, AlreadyExistingException, StorageException, NoNameException, PersistenceException, WrongDataTypeException, NoGenderException;

    boolean updateGlassess(Glasses glasses) throws PersistenceException, StorageException, AlreadyExistingException;

    boolean deleteGlassess(Glasses glasses) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException;

    boolean deleteGlasses(String brand, String model) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException;
}
