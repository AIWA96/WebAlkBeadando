package hu.iit.uni.miskolc.webalk.service;

import hu.iit.uni.miskolc.webalk.core.model.Shop;
import hu.iit.uni.miskolc.webalk.core.service.ShopService;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.ExistingProblemException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.MissingArgumentException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.StorageProblemException;
import hu.iit.uni.miskolc.webalk.service.dao.ShopDAO;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.*;

import java.util.Collection;

public class ShopServiceImpl implements ShopService {

    private ShopDAO shopDAO;

    public ShopServiceImpl(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }

    public void createShop(Shop shop) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException {
        try {
            shopDAO.createShop(shop);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (AlreadyExistingException e) {
            throw new ExistingProblemException();
        } catch (WrongFormatException e) {
            throw new MissingArgumentException();
        }catch (Exception e){
            throw new PersistenceException();
        }
    }

    public Shop getShopByName(String name) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException {
        try {
            return shopDAO.getShopByName(name);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (WrongFormatException e) {
            throw new MissingArgumentException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public Collection<Shop> getShopByLocation(String location) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException {
        try {
            return shopDAO.getShopByLocation(location);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (WrongFormatException e) {
            throw new MissingArgumentException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    @Override
    public Collection<Shop> getAllShops() throws PersistenceException, MissingArgumentException, ExistingProblemException, StorageProblemException {
        try {
            return shopDAO.getAllShops();
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (WrongFormatException e) {
            throw new MissingArgumentException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public boolean updateShop(Shop shop) throws PersistenceException, ExistingProblemException, StorageProblemException {
        try {
            return shopDAO.updateShop(shop);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException | AlreadyExistingException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public boolean deleteShop(String name) throws PersistenceException, ExistingProblemException, StorageProblemException {
        try {
            return shopDAO.deleteShop(name);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public boolean deleteShop(Shop shop) throws PersistenceException, ExistingProblemException, StorageProblemException {
        try {
            return shopDAO.deleteShop(shop);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }
}
