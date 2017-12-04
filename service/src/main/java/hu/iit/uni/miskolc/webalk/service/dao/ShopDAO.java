package hu.iit.uni.miskolc.webalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.model.Shop;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.*;

import java.util.Collection;

public interface ShopDAO {

    void createShop(Shop shop) throws StorageNotAvailableException, StorageException, AlreadyExistingException, WrongFormatException;

    Shop getShopByName(String name) throws StorageNotAvailableException, StorageException, NotFoundException, WrongFormatException;

    Collection<Shop> getShopByLocation(String location) throws StorageNotAvailableException, StorageException, NotFoundException, WrongFormatException;

    Collection<Shop> getAllShops() throws StorageNotAvailableException, StorageException, NotFoundException, WrongFormatException;

    boolean updateShop(Shop shop) throws StorageNotAvailableException, StorageException, NotFoundException, AlreadyExistingException;

    boolean deleteShop(String name) throws StorageNotAvailableException, StorageException, NotFoundException;

    boolean deleteShop(Shop shop) throws StorageNotAvailableException, StorageException, NotFoundException;
}
