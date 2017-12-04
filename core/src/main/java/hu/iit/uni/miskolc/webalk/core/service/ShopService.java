package hu.iit.uni.miskolc.webalk.core.service;

import hu.iit.uni.miskolc.webalk.core.model.Shop;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.ExistingProblemException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.MissingArgumentException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.StorageProblemException;

import java.util.Collection;

public interface ShopService {

    void createShop(Shop shop) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException;

    Shop getShopByName(String name) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException;

    Collection<Shop> getShopByLocation(String location) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException;

    Collection<Shop> getAllShops() throws PersistenceException, MissingArgumentException, ExistingProblemException, StorageProblemException;

    boolean updateShop(Shop shop) throws PersistenceException, ExistingProblemException, StorageProblemException;

    boolean deleteShop(String name) throws PersistenceException, ExistingProblemException, StorageProblemException;

    boolean deleteShop(Shop shop) throws PersistenceException, ExistingProblemException, StorageProblemException;
}
