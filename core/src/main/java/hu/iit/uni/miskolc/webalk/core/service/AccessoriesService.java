package hu.iit.uni.miskolc.webalk.core.service;

import hu.iit.uni.miskolc.webalk.core.model.Accessories;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.ExistingProblemException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.MissingArgumentException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.StorageProblemException;

import java.util.Collection;

public interface AccessoriesService {

    void createAccessories(Accessories accessories) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException;

    Collection<Accessories> getAccessoriesByAppellation(String appellation) throws ExistingProblemException, StorageProblemException, MissingArgumentException, PersistenceException;

    Collection<Accessories> getAccessoriesByBrand(String brand) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException;

    Collection<Accessories> getAllAccessories() throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException;

    boolean updateAccessories(Accessories accessories) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException;

    boolean deleteAccessories(Accessories accessories) throws StorageProblemException, ExistingProblemException, PersistenceException;

    boolean deleteAccessoriesByBrand(String brand) throws StorageProblemException, ExistingProblemException, PersistenceException;
}
