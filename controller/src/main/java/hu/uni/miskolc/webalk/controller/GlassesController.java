package hu.uni.miskolc.webalk.controller;

import hu.iit.uni.miskolc.webalk.controller.dto.GlassesRequest;
import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Glasses;
import hu.iit.uni.miskolc.webalk.core.service.GlassesService;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/glasses")
public class GlassesController {

    @Autowired
    private GlassesService glassesService;

    public GlassesController(GlassesService glassesService) {
        this.glassesService = glassesService;
    }

    @RequestMapping(value = {"/getglass/{brand}"},
            method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<Glasses> listGlassesByBrand(@PathVariable(value = "brand") String brand) {
        try {
            return glassesService.getGlasses(brand);
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

    @RequestMapping(value = {"/getglass/{brand, model}"},
            method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Glasses listGlasses(@PathVariable(value = "brand, model") String brand, String model) {
        try {
            return glassesService.getGlasses(brand, model);
        } catch (NoLocationSetException e) {
            e.printStackTrace();
        } catch (InvalidPriceException e) {
            e.printStackTrace();
        } catch (AlreadyExistingException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (NoNameException e) {
            e.printStackTrace();
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (WrongDataTypeException e) {
            e.printStackTrace();
        } catch (NoGenderException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void add(@RequestBody GlassesRequest glassesRequest) {
        try {
            glassesService.createGlass(new Glasses(glassesRequest.getBrand(), glassesRequest.getModel(),
                    glassesRequest.getPrice(), glassesRequest.getAvailableAt(), glassesRequest.getGender(),
                    glassesRequest.isSunglasses()));
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
        } catch (NoLocationSetException e) {
            e.printStackTrace();
        } catch (InvalidPriceException e) {
            e.printStackTrace();
        } catch (NoGenderException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateglasses", method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean update(@RequestBody GlassesRequest glassesRequest) {
        try {
            return glassesService.updateGlassess(new Glasses(glassesRequest.getBrand(),
                    glassesRequest.getModel(), glassesRequest.getPrice(), glassesRequest.getAvailableAt(),
                    glassesRequest.getGender(), glassesRequest.isSunglasses()));
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (AlreadyExistingException e) {
            e.printStackTrace();
        } catch (NoLocationSetException e) {
            e.printStackTrace();
        } catch (InvalidPriceException e) {
            e.printStackTrace();
        } catch (NoNameException e) {
            e.printStackTrace();
        } catch (NoGenderException e) {
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