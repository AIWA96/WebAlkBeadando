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
     * @throws PersistanceException
     * @throws WrongDataTypeException
     */
    void createGlass(Glasses glasses) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException, PersistanceException, WrongDataTypeException;

    Collection<Glasses> getGlasses(String brand) throws AlreadyExistingException, PersistanceException, StorageException, WrongDataTypeException;
    Glasses getGlasses(String brand, String model) throws NoLocationSetException, InvalidPriceException, AlreadyExistingException, StorageException, NoNameException, PersistanceException, WrongDataTypeException, NoGenderException;

    boolean updateGlassess(Glasses glasses) throws PersistanceException, StorageException, AlreadyExistingException;

    boolean deleteGlassess(Glasses glasses) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException;
    boolean deleteGlasses(String brand, String model) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException;
}
