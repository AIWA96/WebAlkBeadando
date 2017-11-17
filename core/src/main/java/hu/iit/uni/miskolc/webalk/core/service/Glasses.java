package hu.iit.uni.miskolc.webalk.core.service;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Type;

import java.util.Collection;

public interface Glasses {

    Glasses createGlass(String brand, String model, float price, String availableAt, Type type, boolean sunglasses) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException;

    Collection<String> getGlasses(String brand);

    Glasses getGlasses(String brand, String model);

    boolean updateGlassess(String brand);
    boolean updateGlasses(String brand, String model);

    boolean deleteGlassess(String brand);
    boolean deleteGlasses(String brand, String model);
}
