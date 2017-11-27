package hu.iit.uni.miskolc.webalk.service;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Glasses;
import hu.iit.uni.miskolc.webalk.core.service.GlassesService;
import hu.iit.uni.miskolc.webalk.service.dao.GlassesDAO;

import java.util.Collection;

public class GlassesServiceImpl implements GlassesService {

    private GlassesDAO glassesDAO;

    public GlassesServiceImpl(GlassesDAO glassesDAO) {
        this.glassesDAO = glassesDAO;
    }

    public void createGlass(Glasses glasses) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException, PersistenceException, WrongDataTypeException {
        glassesDAO.createGlasses(glasses);
    }

    public Collection<Glasses> getGlasses(String brand) throws AlreadyExistingException, PersistenceException, StorageException, WrongDataTypeException {
        return glassesDAO.getGlasses(brand);
    }

    public Glasses getGlasses(String brand, String model) throws NoLocationSetException, InvalidPriceException, AlreadyExistingException, StorageException, NoNameException, PersistenceException, WrongDataTypeException, NoGenderException {
        return glassesDAO.getGlasses(brand, model);
    }

    public boolean updateGlassess(Glasses glasses) throws PersistenceException, StorageException, AlreadyExistingException {
        return glassesDAO.updateGlasses(glasses);
    }

    public boolean deleteGlassess(Glasses glasses) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException {
        return glassesDAO.deleteGlasses(glasses);
    }

    public boolean deleteGlasses(String brand, String model) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException {
        return glassesDAO.deleteGlasses(brand, model);
    }
}
