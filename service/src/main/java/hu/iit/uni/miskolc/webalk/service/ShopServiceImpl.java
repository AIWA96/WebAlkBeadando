package hu.iit.uni.miskolc.webalk.service;

import hu.iit.uni.miskolc.webalk.core.model.Shop;
import hu.iit.uni.miskolc.webalk.core.service.ShopService;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.ExistingProblemException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.StorageProblemException;
import hu.iit.uni.miskolc.webalk.service.dao.ShopDAO;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.AlreadyExistException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.NotFoundException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.StorageException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.StorageNotAvailableException;

import java.util.Collection;

public class ShopServiceImpl implements ShopService {

    private ShopDAO shopDAO;

    public ShopServiceImpl(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }

    public void createShop(Shop shop) throws StorageProblemException, ExistingProblemException, PersistenceException {
        try {
            shopDAO.createShop(shop);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException(e);
        } catch (AlreadyExistException e) {
            throw new ExistingProblemException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    public Shop getShopByName(String name) throws StorageProblemException, ExistingProblemException, PersistenceException {
        try {
            return shopDAO.getShopByName(name);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException(e);
        } catch (NotFoundException e) {
            throw new ExistingProblemException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    public Collection<Shop> getShopByLocation(String location) throws StorageProblemException, ExistingProblemException, PersistenceException {
        try {
            return shopDAO.getShopByLocation(location);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException(e);
        } catch (NotFoundException e) {
            throw new ExistingProblemException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    public Collection<Shop> getAllShops() throws PersistenceException, ExistingProblemException, StorageProblemException {
        try {
            return shopDAO.getAllShops();
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException(e);
        } catch (NotFoundException e) {
            throw new ExistingProblemException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    public boolean updateShop(Shop shop) throws PersistenceException, ExistingProblemException, StorageProblemException {
        try {
            return shopDAO.updateShop(shop);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException(e);
        } catch (NotFoundException | AlreadyExistException e) {
            throw new ExistingProblemException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    public boolean deleteShop(String name) throws PersistenceException, ExistingProblemException, StorageProblemException {
        try {
            return shopDAO.deleteShop(name);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException(e);
        } catch (NotFoundException e) {
            throw new ExistingProblemException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    public boolean deleteShop(Shop shop) throws PersistenceException, ExistingProblemException, StorageProblemException {
        try {
            return shopDAO.deleteShop(shop);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException(e);
        } catch (NotFoundException e) {
            throw new ExistingProblemException(e);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }
}
