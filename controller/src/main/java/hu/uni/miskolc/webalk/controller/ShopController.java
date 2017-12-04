package hu.uni.miskolc.webalk.controller;

import hu.iit.uni.miskolc.webalk.controller.dto.ShopRequest;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoEmployeeException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoLocationException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;
import hu.iit.uni.miskolc.webalk.core.model.Shop;
import hu.iit.uni.miskolc.webalk.core.service.ShopService;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.ExistingProblemException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.MissingArgumentException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.StorageProblemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(value = {"/getshops"},
            method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<Shop> listAllShops() throws MissingArgumentException, PersistenceException, ExistingProblemException, StorageProblemException {
        return shopService.getAllShops();
    }

    @RequestMapping(value = {"/getshop/{location}"},
            method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<Shop> listGlassesByBrand(@PathVariable(value = "location") String location) throws ExistingProblemException, StorageProblemException, MissingArgumentException, PersistenceException {
        return shopService.getShopByLocation(location);
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void add(@RequestBody ShopRequest shopRequest) throws NoEmployeeException, NoNameException, NoLocationException, ExistingProblemException, StorageProblemException, MissingArgumentException, PersistenceException {
        Collection collection = shopRequest.getEmployees();
        shopService.createShop(new Shop(shopRequest.getShopName(), shopRequest.getLocation(), collection));
    }

    @RequestMapping(value = "/updateshop", method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean update(@RequestBody ShopRequest shopRequest) throws NoEmployeeException, NoNameException, NoLocationException, StorageProblemException, ExistingProblemException, PersistenceException {
        Collection collection = shopRequest.getEmployees();
        return shopService.updateShop(new Shop(shopRequest.getShopName(), shopRequest.getLocation(), collection));
    }


    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason = "Adatbázis hiba!")
    @ExceptionHandler({StorageProblemException.class})
    public void dataBaseError() {
    }

    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Nem adtál meg minden adatot!")
    @ExceptionHandler({NoEmployeeException.class, NoNameException.class, NoLocationException.class, MissingArgumentException.class})
    public void missingArgumentError() {
    }

    @ResponseStatus(value = HttpStatus.ALREADY_REPORTED, reason = "Már létező adat")
    @ExceptionHandler({ExistingProblemException.class})
    public void existingError() {
    }

    @ExceptionHandler({PersistenceException.class})
    public String errorOccurred() {
        return "Valami gond van :(";
    }
}
