package hu.iit.uni.miskolc.webalk.service;

import hu.iit.uni.miskolc.webalk.core.model.Accessories;
import hu.iit.uni.miskolc.webalk.core.service.AccessoriesService;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.ExistingProblemException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.MissingArgumentException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.StorageProblemException;
import hu.iit.uni.miskolc.webalk.service.dao.AccessoriesDAO;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.AlreadyExistingException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.NotFoundException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.StorageException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.StorageNotAvailableException;

import java.util.Collection;

public class AccessoriesServiceImpl implements AccessoriesService {

    private AccessoriesDAO accessoriesDAO;

    public AccessoriesServiceImpl(AccessoriesDAO accessoriesDAO) {
        this.accessoriesDAO = accessoriesDAO;
    }

    public void createAccessories(Accessories accessories) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException {
        try {
            accessoriesDAO.createAccessories(accessories);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (AlreadyExistingException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public Collection<Accessories> getAccessoriesByAppellation(String appellation) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException {
        try {
            return accessoriesDAO.getAccessoriesByAppellation(appellation);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public Collection<Accessories> getAccessoriesByBrand(String brand) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException {
        try {
            return accessoriesDAO.getAccessoriesByBrand(brand);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public Collection<Accessories> getAllAccessories() throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException {
        try {
            return accessoriesDAO.getAllAccessories();
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public boolean updateAccessories(Accessories accessories) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException {
        try {
            return accessoriesDAO.updateAccessories(accessories);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException | AlreadyExistingException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public boolean deleteAccessories(Accessories accessories) throws StorageProblemException, ExistingProblemException, PersistenceException {
        try {
            return accessoriesDAO.deleteAccessories(accessories);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public boolean deleteAccessoriesByBrand(String brand) throws StorageProblemException, ExistingProblemException, PersistenceException {
        try {
            return accessoriesDAO.deleteAccessoriesByBrand(brand);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }
}
