package hu.iit.uni.miskolc.webalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Shop;
import java.util.Collection;

public interface ShopDAO {
    void createShop(Shop shop) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistenceException;

    Shop getShopByName(String name) throws NoEmployeeException, NoNameException, NoLocationException, AlreadyExistingException, WrongDataTypeException, StorageException, PersistenceException;
    Collection<Shop> getShopByLocation(String location) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistenceException;
    Collection<Shop> getAllShops() throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistenceException;

    boolean updateShop(Shop shop) throws NotFoundException, StorageNotAvailableException, AlreadyExistingException, StorageException, ClassNotFoundException;

    boolean deleteShop(String name) throws AlreadyExistingException, StorageException, StorageNotAvailableException, ClassNotFoundException, NotFoundException;
    boolean deleteShop(Shop shop) throws AlreadyExistingException, StorageNotAvailableException, StorageException, ClassNotFoundException, NotFoundException;
}
