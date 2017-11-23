package hu.iit.uni.miskolc.wabalk.service;

import hu.iit.uni.miskolc.wabalk.service.dao.GlassesDAO;
import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Glasses;
import hu.iit.uni.miskolc.webalk.core.service.GlassesService;

import java.util.Collection;

public class GlassesServiceImpl implements GlassesService {

    private GlassesDAO glassesDAO;

    public GlassesServiceImpl(GlassesDAO glassesDAO) {
        this.glassesDAO = glassesDAO;
    }

    public void createGlass(Glasses glasses) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException, PersistanceException, WrongDataTypeException {
        glassesDAO.createGlasses(glasses);
    }

    public Collection<Glasses> getGlasses(String brand) throws AlreadyExistingException, PersistanceException, StorageException, WrongDataTypeException {
        return glassesDAO.getGlasses(brand);
    }

    public Glasses getGlasses(String brand, String model) throws NoLocationSetException, InvalidPriceException, AlreadyExistingException, StorageException, NoNameException, PersistanceException, WrongDataTypeException, NoGenderException {
        return glassesDAO.getGlasses(brand, model);
    }

    public boolean updateGlassess(Glasses glasses) throws PersistanceException, StorageException, AlreadyExistingException {
        return glassesDAO.updateGlasses(glasses);
    }

    public boolean deleteGlassess(Glasses glasses) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException {
        return glassesDAO.deleteGlasses(glasses);
    }

    public boolean deleteGlasses(String brand, String model) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException {
        return glassesDAO.deleteGlasses(brand, model);
    }
}
