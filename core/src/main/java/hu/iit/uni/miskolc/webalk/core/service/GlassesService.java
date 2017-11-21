package hu.iit.uni.miskolc.webalk.core.service;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
<<<<<<< HEAD
=======
import hu.iit.uni.miskolc.webalk.core.model.Gender;
import hu.iit.uni.miskolc.webalk.core.model.Type;
>>>>>>> master
import hu.iit.uni.miskolc.webalk.core.model.Glasses;

import java.util.Collection;

public interface GlassesService {

<<<<<<< HEAD
    Glasses createGlass(String brand, String model, float price, String availableAt, String gender, boolean sunglasses) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException;
=======
    Glasses createGlass(String brand, String model, float price, String availableAt, Gender gender, boolean sunglasses) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException;
>>>>>>> master

    Collection<String> getGlasses(String brand);

    Glasses getGlasses(String brand, String model);

    boolean updateGlassess(String brand);
    boolean updateGlasses(String brand, String model);

    boolean deleteGlassess(String brand);
    boolean deleteGlasses(String brand, String model);
}
