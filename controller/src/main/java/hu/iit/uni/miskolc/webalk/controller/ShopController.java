package hu.iit.uni.miskolc.webalk.controller;

import hu.iit.uni.miskolc.webalk.controller.dto.ShopRequest;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoEmployeeException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoLocationException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;
import hu.iit.uni.miskolc.webalk.core.model.Employee;
import hu.iit.uni.miskolc.webalk.core.model.Shop;
import hu.iit.uni.miskolc.webalk.core.service.EmployeeService;
import hu.iit.uni.miskolc.webalk.core.service.ShopService;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.ExistingProblemException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.MissingArgumentException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.StorageProblemException;
import hu.iit.uni.miskolc.webalk.dao.DataBase;
import hu.iit.uni.miskolc.webalk.dao.EmployeeDAOsql;
import hu.iit.uni.miskolc.webalk.service.EmployeeServiceImpl;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.AlreadyExistException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.CreateDataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    public ShopController(ShopService shopService) throws CreateDataBaseException {
        this.shopService = shopService;
        if (DataBase.isDBNotCreated()){
            throw new CreateDataBaseException(DataBase.getCause());
        }
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

    /*@RequestMapping(value = {"/add"}, method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void add(@RequestBody ShopRequest shopRequest) throws NoEmployeeException, NoNameException, NoLocationException, ExistingProblemException, StorageProblemException, MissingArgumentException, PersistenceException {
        Collection<ShopRequest.Employee> collection = shopRequest.getEmployee();
        shopService.createShop(new Shop(shopRequest.getShopName(), shopRequest.getLocation(), collection));
    }*/

    @RequestMapping(value = "/updateshop", method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean update(@RequestBody ShopRequest shopRequest) throws NoEmployeeException, NoNameException, NoLocationException, StorageProblemException, ExistingProblemException, PersistenceException, MissingArgumentException {
        EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOsql());
        Collection<Employee> collection = employeeService.getEmployeeByShopName(shopRequest.getShopName());

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

    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED, reason = "Az adatbázis létrehozása sikertelen!")
    @ExceptionHandler({CreateDataBaseException.class, AlreadyExistException.class})
    public void noDataBaseError() {
    }

    @ExceptionHandler({PersistenceException.class})
    public String errorOccurred() {
        return "Valami gond van :(";
    }
}
