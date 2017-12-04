package hu.iit.uni.miskolc.webalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.model.Glasses;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.*;

import java.util.Collection;

public interface GlassesDAO {

    void createGlasses(Glasses glasses) throws StorageNotAvailableException, StorageException, AlreadyExistingException, WrongFormatException;

    Collection<Glasses> getGlasses(String brand) throws StorageNotAvailableException, StorageException, NotFoundException, WrongFormatException;

    Glasses getGlasses(String brand, String model) throws StorageNotAvailableException, StorageException, NotFoundException, WrongFormatException;

    boolean updateGlasses(Glasses glasses) throws StorageNotAvailableException, StorageException, NotFoundException, AlreadyExistingException;

    boolean deleteGlasses(String brand, String model) throws StorageNotAvailableException, StorageException, NotFoundException;

    boolean deleteGlasses(Glasses glasses) throws StorageNotAvailableException, StorageException, NotFoundException;
}
