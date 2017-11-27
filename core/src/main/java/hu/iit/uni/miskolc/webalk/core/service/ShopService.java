package hu.iit.uni.miskolc.webalk.core.service;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Shop;

import java.util.Collection;

public interface ShopService {

    /**
     * @param shop
     * @throws AlreadyExistingException
     * @throws StorageNotAvailableException
     * @throws StorageException
     * @throws NotFoundException
     * @throws NoNameException
     * @throws PersistenceException
     * @throws WrongDataTypeException
     */
    void createShop(Shop shop) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException, PersistenceException, WrongDataTypeException;

    /**
     * @param name
     * @return Shop
     */
    Shop getShopByName(String name) throws WrongDataTypeException, NoEmployeeException, NoLocationException, StorageException, NoNameException, PersistenceException, AlreadyExistingException;
    Collection<Shop> getShopByLocation(String location) throws WrongDataTypeException, NoEmployeeException, NoLocationException, StorageException, NoNameException, PersistenceException, AlreadyExistingException;
    Collection<Shop> getAllShops() throws AlreadyExistingException, PersistenceException, StorageException, WrongDataTypeException;

    boolean updateShop(Shop shop) throws AlreadyExistingException, StorageNotAvailableException, StorageException, ClassNotFoundException, NotFoundException;

    boolean deleteShop(String name) throws AlreadyExistingException, StorageNotAvailableException, StorageException, ClassNotFoundException, NotFoundException;
    boolean deleteShop(Shop shop) throws AlreadyExistingException, StorageNotAvailableException, StorageException, ClassNotFoundException, NotFoundException;
}
