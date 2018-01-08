package hu.uni.miskolc.webalk.controller;

import hu.iit.uni.miskolc.webalk.controller.dto.GlassesRequest;
import hu.iit.uni.miskolc.webalk.core.exceptions.InvalidPriceException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoGenderException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoLocationSetException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;
import hu.iit.uni.miskolc.webalk.core.model.Glasses;
import hu.iit.uni.miskolc.webalk.core.service.GlassesService;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.ExistingProblemException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.MissingArgumentException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.StorageProblemException;
import hu.iit.uni.miskolc.webalk.dao.DataBase;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.AlreadyExistException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.CreateDataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/glasses")
public class GlassesController {

    @Autowired
    private GlassesService glassesService;

    public GlassesController(GlassesService glassesService) throws CreateDataBaseException {
        this.glassesService = glassesService;
        if (DataBase.isDBCreated() == false){
            throw new CreateDataBaseException(DataBase.getCause());
        }
    }

    @RequestMapping(value = {"/getglasses/{brand}"},
            method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<Glasses> listGlassesByBrand(@PathVariable(value = "brand") String brand) throws ExistingProblemException, StorageProblemException, MissingArgumentException, PersistenceException {
        return glassesService.getGlasses(brand);
    }

    /*@RequestMapping(value = {"/getglass/{brand}/{model}"},
            method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Glasses listGlasses(@PathVariable String brand, @PathVariable String model) throws ExistingProblemException, StorageProblemException, MissingArgumentException, PersistenceException {
        System.out.println(brand + "\n" + model);
        return glassesService.getGlasses(brand, model);
    }*/

    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void add(@RequestBody GlassesRequest glassesRequest) throws InvalidPriceException, NoGenderException, NoNameException, NoLocationSetException, ExistingProblemException, StorageProblemException, MissingArgumentException, PersistenceException {
        glassesService.createGlass(new Glasses(glassesRequest.getBrand(), glassesRequest.getModel(),
                glassesRequest.getPrice(), glassesRequest.getAvailableAt(), glassesRequest.getGender(),
                glassesRequest.isSunglasses()));
    }

    @RequestMapping(value = "/updateglasses", method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean update(@RequestBody GlassesRequest glassesRequest) throws InvalidPriceException, NoGenderException, NoNameException, NoLocationSetException, StorageProblemException, ExistingProblemException, PersistenceException {
        return glassesService.updateGlasses(new Glasses(glassesRequest.getBrand(),
                glassesRequest.getModel(), glassesRequest.getPrice(), glassesRequest.getAvailableAt(),
                glassesRequest.getGender(), glassesRequest.isSunglasses()));
    }

    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason = "Adatbázis hiba!")
    @ExceptionHandler({StorageProblemException.class})
    public void dataBaseError() {
    }

    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Nem adtál meg minden adatot!")
    @ExceptionHandler({InvalidPriceException.class, NoGenderException.class, NoNameException.class, NoLocationSetException.class, MissingArgumentException.class})
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