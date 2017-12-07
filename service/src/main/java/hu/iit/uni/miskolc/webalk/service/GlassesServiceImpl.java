package hu.iit.uni.miskolc.webalk.service;

import hu.iit.uni.miskolc.webalk.core.model.Glasses;
import hu.iit.uni.miskolc.webalk.core.service.GlassesService;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.ExistingProblemException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.MissingArgumentException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.StorageProblemException;
import hu.iit.uni.miskolc.webalk.service.dao.GlassesDAO;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.AlreadyExistException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.NotFoundException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.StorageException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.StorageNotAvailableException;

import java.util.Collection;

public class GlassesServiceImpl implements GlassesService {

    private GlassesDAO glassesDAO;

    public GlassesServiceImpl(GlassesDAO glassesDAO) {
        this.glassesDAO = glassesDAO;
    }

    public void createGlass(Glasses glasses) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException {
        try {
            glassesDAO.createGlasses(glasses);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (AlreadyExistException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public Collection<Glasses> getGlasses(String brand) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException {
        try {
            return glassesDAO.getGlasses(brand);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public Glasses getGlasses(String brand, String model) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException {
        try {
            return glassesDAO.getGlasses(brand, model);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public boolean updateGlasses(Glasses glasses) throws StorageProblemException, ExistingProblemException, PersistenceException {
        try {
            return glassesDAO.updateGlasses(glasses);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException | AlreadyExistException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public boolean deleteGlassess(Glasses glasses) throws StorageProblemException, ExistingProblemException, PersistenceException {
        try {
            return glassesDAO.deleteGlasses(glasses);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public boolean deleteGlasses(String brand, String model) throws StorageProblemException, ExistingProblemException, PersistenceException {
        try {
            return glassesDAO.deleteGlasses(brand, model);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }
}
