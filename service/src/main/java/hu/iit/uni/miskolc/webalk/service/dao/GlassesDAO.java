package hu.iit.uni.miskolc.webalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.model.Glasses;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.*;

import java.util.Collection;

public interface GlassesDAO {

    void createGlasses(Glasses glasses) throws StorageNotAvailableException, StorageException, AlreadyExistingException, PersistenceException;

    Collection<Glasses> getGlasses(String brand) throws StorageNotAvailableException, StorageException, NotFoundException, PersistenceException;

    Glasses getGlasses(String brand, String model) throws StorageNotAvailableException, StorageException, NotFoundException, PersistenceException, NoArgumentException;

    boolean updateGlasses(Glasses glasses) throws StorageNotAvailableException, StorageException, NotFoundException, AlreadyExistingException, PersistenceException;

    boolean deleteGlasses(String brand, String model) throws StorageNotAvailableException, StorageException, NotFoundException, PersistenceException;

    boolean deleteGlasses(Glasses glasses) throws StorageNotAvailableException, StorageException, NotFoundException, PersistenceException;
}
