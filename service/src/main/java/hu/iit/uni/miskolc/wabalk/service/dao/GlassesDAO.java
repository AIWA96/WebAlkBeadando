package hu.iit.uni.miskolc.wabalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Glasses;

import java.util.Collection;

public interface GlassesDAO {
    void createGlasses(Glasses glasses) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException;

    Collection<Glasses> getGlasses(String brand) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException;
    Glasses getGlasses(String brand, String model) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException, InvalidPriceException, NoGenderException, NoNameException, NoLocationSetException;

    boolean updateGlasses(Glasses glasses) throws AlreadyExistingException, StorageException, PersistanceException;

    boolean deleteGlasses(String brand, String model) throws ClassNotFoundException, NotFoundException, AlreadyExistingException, StorageException;
    boolean deleteGlasses(Glasses glasses) throws ClassNotFoundException, StorageException, NotFoundException, AlreadyExistingException;
}
