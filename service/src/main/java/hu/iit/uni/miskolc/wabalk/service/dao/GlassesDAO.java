package hu.iit.uni.miskolc.wabalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.model.Glasses;

import java.util.Collection;

public interface GlassesDAO {
    void createGlasses(Glasses glasses);

    Collection<Glasses> getGlasses(String brand);
    Glasses getGlasses(String brand, String model);

    boolean updateGlasses(Glasses glasses);

    boolean deleteGlasses(String brand);
    boolean deleteGlasses(Glasses glasses);
}
