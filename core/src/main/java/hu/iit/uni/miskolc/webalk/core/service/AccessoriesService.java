package hu.iit.uni.miskolc.webalk.core.service;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Accessories;

import java.util.Collection;

public interface AccessoriesService {

    Accessories createAccessories(String appellation, String name, float price) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException;

    Collection<Accessories> getAccessories(String appellation);
    Accessories getAccessories(String appellation, String name);

    boolean updateAccessoires(String appellation);
    boolean updateAccessoires(String appellation, String name);

    boolean deleteAccessoires(String appellation);
    boolean deleteAccessoires(String appellation, String name);
}
