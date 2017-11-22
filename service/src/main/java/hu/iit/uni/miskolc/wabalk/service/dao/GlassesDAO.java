package hu.iit.uni.miskolc.wabalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.exceptions.AlreadyExistingException;
import hu.iit.uni.miskolc.webalk.core.exceptions.PersistanceException;
import hu.iit.uni.miskolc.webalk.core.exceptions.StorageException;
import hu.iit.uni.miskolc.webalk.core.exceptions.WrongDataTypeException;
import hu.iit.uni.miskolc.webalk.core.model.Glasses;

import java.util.Collection;

public interface GlassesDAO {
    void createGlasses(Glasses glasses) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException;

    Collection<Glasses> getGlasses(String brand);
    Glasses getGlasses(String brand, String model);

    boolean updateGlasses(Glasses glasses);

    boolean deleteGlasses(String brand);
    boolean deleteGlasses(Glasses glasses);
}
