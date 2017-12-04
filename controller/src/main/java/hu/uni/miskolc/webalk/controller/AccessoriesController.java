package hu.uni.miskolc.webalk.controller;

import hu.iit.uni.miskolc.webalk.controller.dto.AccessoriesRequest;
import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Accessories;
import hu.iit.uni.miskolc.webalk.core.service.AccessoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/accessories")
public class AccessoriesController {

    @Autowired
    private AccessoriesService accessoriesService;

    public AccessoriesController(AccessoriesService accessoriesService) {
        this.accessoriesService = accessoriesService;
    }

    @RequestMapping(value = {"/getaccessories"}, method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<Accessories> listAllAccessories() {
        try {
            return accessoriesService.getAllAccessories();
        } catch (AlreadyExistingException e) {
            e.printStackTrace();
        } catch (WrongDataTypeException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = {"/getaccessories/{appellation}"},
            method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)

    @ResponseBody
    public Collection<Accessories> listAccessoriesByAppellation(
            @PathVariable(value = "appellation") String appellation) {
        try {
            return accessoriesService.getAccessoriesByAppellation(appellation);
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

    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void add(@RequestBody AccessoriesRequest accessoriesRequest) {
        try {
            accessoriesService.createAccessories(new Accessories(accessoriesRequest.getAppellation(),
                    accessoriesRequest.getBrand(), accessoriesRequest.getPrice()));
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
        } catch (NoAppellationException e) {
            e.printStackTrace();
        } catch (InvalidPriceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateaccessori", method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean update(@RequestBody AccessoriesRequest accessoriesRequest) {
        try {
            return accessoriesService.updateAccessories(new Accessories(accessoriesRequest.getAppellation(),
                    accessoriesRequest.getBrand(), accessoriesRequest.getPrice()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (AlreadyExistingException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (NoAppellationException e) {
            e.printStackTrace();
        } catch (NoNameException e) {
            e.printStackTrace();
        } catch (InvalidPriceException e) {
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
