package hu.iit.uni.miskolc.webalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.model.Shop;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.*;

import java.util.Collection;

public interface ShopDAO {

    void createShop(Shop shop) throws StorageNotAvailableException, StorageException, AlreadyExistingException, PersistenceException;

    Shop getShopByName(String name) throws StorageNotAvailableException, StorageException, NotFoundException, PersistenceException, NoArgumentException;

    Collection<Shop> getShopByLocation(String location) throws StorageNotAvailableException, StorageException, NotFoundException, NoArgumentException,PersistenceException;

    Collection<Shop> getAllShops() throws StorageNotAvailableException, StorageException, NotFoundException, NoArgumentException, PersistenceException;

    boolean updateShop(Shop shop) throws StorageNotAvailableException, StorageException, NotFoundException, AlreadyExistingException, PersistenceException;

    boolean deleteShop(String name) throws StorageNotAvailableException, StorageException, NotFoundException, PersistenceException;

    boolean deleteShop(Shop shop) throws StorageNotAvailableException, StorageException, NotFoundException, PersistenceException;
}
