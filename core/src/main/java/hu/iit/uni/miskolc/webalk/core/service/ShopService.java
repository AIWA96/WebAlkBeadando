package hu.iit.uni.miskolc.webalk.core.service;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Employee;
import hu.iit.uni.miskolc.webalk.core.model.Shop;

import java.util.Collection;

public interface ShopService {

    /**
     * @param name
     * @param location
     * @param employees
     * @return
     * @throws AlreadyExistingException
     * @throws StorageNotAvailableException
     * @throws StorageException
     * @throws NotFoundException
     * @throws NoNameException
     */
    Shop createShop(String name, String location, Collection<Employee> employees) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException;

    /**
     * @param name
     * @return Shop
     */
    Shop getShop(String name);

    boolean updateShop(String name);
    boolean updateShop(Shop shop);

    boolean deleteShop(String name);
    boolean deleteShop(Shop shop);
}
