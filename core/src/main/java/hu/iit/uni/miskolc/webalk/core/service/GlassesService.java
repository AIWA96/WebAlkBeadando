package hu.iit.uni.miskolc.webalk.core.service;

import hu.iit.uni.miskolc.webalk.core.model.Glasses;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.ExistingProblemException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.MissingArgumentException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.StorageProblemException;

import java.util.Collection;

public interface GlassesService {

    void createGlass(Glasses glasses) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException;

    Collection<Glasses> getGlasses(String brand) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException;

    Glasses getGlasses(String brand, String model) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException;

    boolean updateGlasses(Glasses glasses) throws StorageProblemException, ExistingProblemException, PersistenceException;

    boolean deleteGlassess(Glasses glasses) throws StorageProblemException, ExistingProblemException, PersistenceException;

    boolean deleteGlasses(String brand, String model) throws StorageProblemException, ExistingProblemException, PersistenceException;
}
