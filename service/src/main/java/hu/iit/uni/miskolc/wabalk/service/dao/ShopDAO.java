package hu.iit.uni.miskolc.wabalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.exceptions.NoEmployeeException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoLocationException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;
import hu.iit.uni.miskolc.webalk.core.model.Shop;
import java.util.Collection;

public interface ShopDAO {
    void createShop(Shop shop);

    Shop getShopByName(String name) throws NoEmployeeException, NoNameException, NoLocationException;
    Collection<Shop> getShopByLocation(String location);

    boolean updateShop(String name);
    boolean updateShop(Shop shop);

    boolean deleteShop(String name);
    boolean deleteShop(Shop shop);
}
