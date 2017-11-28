package hu.uni.miskolc.webalk.controller;

import hu.iit.uni.miskolc.webalk.controller.dto.ShopRequest;
import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Shop;
import hu.iit.uni.miskolc.webalk.core.service.ShopService;
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
    public Collection<Shop> listAllShops() {
        try {
            return shopService.getAllShops();
        } catch (AlreadyExistingException e) {
            e.printStackTrace();
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (WrongDataTypeException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = {"/getshop/{location}"},
            method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<Shop> listGlassesByBrand(@PathVariable(value = "location") String location) {
        try {
            return shopService.getShopByLocation(location);
        } catch (WrongDataTypeException e) {
            e.printStackTrace();
        } catch (NoEmployeeException e) {
            e.printStackTrace();
        } catch (NoLocationException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (NoNameException e) {
            e.printStackTrace();
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (AlreadyExistingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void add(@RequestBody ShopRequest shopRequest) {
        try {
            Collection collection = shopRequest.getEmployees();
            shopService.createShop(new Shop(shopRequest.getShopName(), shopRequest.getLocation(), collection));
        } catch (AlreadyExistingException e) {
            e.printStackTrace();
        } catch (StorageNotAvailableException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (NoNameException e) {
            e.printStackTrace();
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (WrongDataTypeException e) {
            e.printStackTrace();
        } catch (NoLocationException e) {
            e.printStackTrace();
        } catch (NoEmployeeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateshop", method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean update(@RequestBody ShopRequest shopRequest) {
        try {
            Collection collection = shopRequest.getEmployees();
            return shopService.updateShop(new Shop(shopRequest.getShopName(), shopRequest.getLocation(), collection));
        } catch (AlreadyExistingException e) {
            e.printStackTrace();
        } catch (StorageNotAvailableException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (NoNameException e) {
            e.printStackTrace();
        } catch (NoLocationException e) {
            e.printStackTrace();
        } catch (NoEmployeeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason = "Adatbázis hiba!")
    @ExceptionHandler(StorageException.class)
    public void dataBaseError() {

    }

    @ExceptionHandler(PersistenceException.class)
    public String errorOccurred() {
        return "error";
    }
}
