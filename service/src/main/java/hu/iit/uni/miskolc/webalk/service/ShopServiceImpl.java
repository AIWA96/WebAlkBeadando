package hu.iit.uni.miskolc.webalk.service;

import hu.iit.uni.miskolc.webalk.service.dao.ShopDAO;
import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Shop;
import hu.iit.uni.miskolc.webalk.core.service.ShopService;

import java.util.Collection;

public class ShopServiceImpl implements ShopService {

    private ShopDAO shopDAO;

    public ShopServiceImpl(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }

    public void createShop(Shop shop) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException, PersistenceException, WrongDataTypeException {
        shopDAO.createShop(shop);
    }

    public Shop getShopByName(String name) throws WrongDataTypeException, NoEmployeeException, NoLocationException, StorageException, NoNameException, PersistenceException, AlreadyExistingException {
        return shopDAO.getShopByName(name);
    }

    public Collection<Shop> getShopByLocation(String location) throws WrongDataTypeException, NoEmployeeException, NoLocationException, StorageException, NoNameException, PersistenceException, AlreadyExistingException {
        return shopDAO.getShopByLocation(location);
    }

    @Override
    public Collection<Shop> getAllShops() throws AlreadyExistingException, PersistenceException, StorageException, WrongDataTypeException {
        return shopDAO.getAllShops();
    }

    public boolean updateShop(Shop shop) throws AlreadyExistingException, StorageNotAvailableException, StorageException, ClassNotFoundException, NotFoundException {
        return shopDAO.updateShop(shop);
    }

    public boolean deleteShop(String name) throws AlreadyExistingException, StorageNotAvailableException, StorageException, ClassNotFoundException, NotFoundException {
        return shopDAO.deleteShop(name);
    }

    public boolean deleteShop(Shop shop) throws AlreadyExistingException, StorageNotAvailableException, StorageException, ClassNotFoundException, NotFoundException {
        return shopDAO.deleteShop(shop);
    }
}
