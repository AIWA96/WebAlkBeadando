package hu.iit.uni.miskolc.wabalk.service;

import hu.iit.uni.miskolc.wabalk.service.dao.AccessoriesDAO;
import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Accessories;
import hu.iit.uni.miskolc.webalk.core.service.AccessoriesService;

import java.util.Collection;

public class AccessoriesServiceImpl implements AccessoriesService {

    private AccessoriesDAO accessoriesDAO;

    public AccessoriesServiceImpl(AccessoriesDAO accessoriesDAO) {
        this.accessoriesDAO = accessoriesDAO;
    }

    public void createAccessories(Accessories accessories) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException, PersistanceException, WrongDataTypeException {
        accessoriesDAO.createAccessories(accessories);
    }

    public Collection<Accessories> getAccessoriesByAppellation(String appellation) throws AlreadyExistingException, PersistanceException, StorageException, WrongDataTypeException {
        return accessoriesDAO.getAccessoriesByAppellation(appellation);
    }

    public Collection<Accessories> getAccessoriesByBrand(String brand) throws AlreadyExistingException, PersistanceException, StorageException, WrongDataTypeException {
        return accessoriesDAO.getAccessoriesByBrand(brand);
    }

    public boolean updateAccessories(Accessories accessories) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException, PersistanceException {
        return accessoriesDAO.updateAccessories(accessories);
    }

    public boolean deleteAccessories(Accessories accessories) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException {
        return accessoriesDAO.deleteAccessories(accessories);
    }

    public boolean deleteAccessories(String brand) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException {
        return accessoriesDAO.deleteAccessoriesByBrand(brand);
    }
}
