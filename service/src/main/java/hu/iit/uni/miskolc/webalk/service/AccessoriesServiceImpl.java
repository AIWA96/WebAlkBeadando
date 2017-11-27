package hu.iit.uni.miskolc.webalk.service;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Accessories;
import hu.iit.uni.miskolc.webalk.core.service.AccessoriesService;
import hu.iit.uni.miskolc.webalk.service.dao.AccessoriesDAO;

import java.util.Collection;

public class AccessoriesServiceImpl implements AccessoriesService {

    private AccessoriesDAO accessoriesDAO;

    public AccessoriesServiceImpl(AccessoriesDAO accessoriesDAO) {
        this.accessoriesDAO = accessoriesDAO;
    }

    public void createAccessories(Accessories accessories) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException, PersistenceException, WrongDataTypeException {
        accessoriesDAO.createAccessories(accessories);
    }

    public Collection<Accessories> getAccessoriesByAppellation(String appellation) throws AlreadyExistingException, PersistenceException, StorageException, WrongDataTypeException {
        return accessoriesDAO.getAccessoriesByAppellation(appellation);
    }

    public Collection<Accessories> getAccessoriesByBrand(String brand) throws AlreadyExistingException, PersistenceException, StorageException, WrongDataTypeException {
        return accessoriesDAO.getAccessoriesByBrand(brand);
    }

    public Collection<Accessories> getAllAccessories() throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistenceException {
        return accessoriesDAO.getAllAccessories();
    }

    public boolean updateAccessories(Accessories accessories) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException, PersistenceException {
        return accessoriesDAO.updateAccessories(accessories);
    }

    public boolean deleteAccessories(Accessories accessories) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException {
        return accessoriesDAO.deleteAccessories(accessories);
    }

    public boolean deleteAccessories(String brand) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException {
        return accessoriesDAO.deleteAccessoriesByBrand(brand);
    }
}
