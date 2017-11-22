package hu.iit.uni.miskolc.wabalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Shop;
import java.util.Collection;

public interface ShopDAO {
    void createShop(Shop shop) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException;

    Shop getShopByName(String name) throws NoEmployeeException, NoNameException, NoLocationException, AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException;
    Collection<Shop> getShopByLocation(String location) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException;

    boolean updateShop(Shop shop) throws NotFoundException, StorageNotAvailableException, AlreadyExistingException, StorageException, ClassNotFoundException;

    boolean deleteShop(String name) throws AlreadyExistingException, StorageException, StorageNotAvailableException, ClassNotFoundException, NotFoundException;
    boolean deleteShop(Shop shop) throws AlreadyExistingException, StorageNotAvailableException, StorageException, ClassNotFoundException, NotFoundException;
}
