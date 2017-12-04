package hu.uni.miskolc.webalk.controller;

import hu.iit.uni.miskolc.webalk.controller.dto.AccessoriesRequest;
import hu.iit.uni.miskolc.webalk.core.exceptions.InvalidPriceException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoAppellationException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;
import hu.iit.uni.miskolc.webalk.core.model.Accessories;
import hu.iit.uni.miskolc.webalk.core.service.AccessoriesService;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.ExistingProblemException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.MissingArgumentException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.StorageProblemException;
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
    public Collection<Accessories> listAllAccessories() throws ExistingProblemException, StorageProblemException, MissingArgumentException, PersistenceException {
        return accessoriesService.getAllAccessories();
    }

    @RequestMapping(value = {"/getaccessories/{appellation}"},
            method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)

    @ResponseBody
    public Collection<Accessories> listAccessoriesByAppellation(
            @PathVariable(value = "appellation") String appellation) throws ExistingProblemException, StorageProblemException, MissingArgumentException, PersistenceException {
        return accessoriesService.getAccessoriesByAppellation(appellation);
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void add(@RequestBody AccessoriesRequest accessoriesRequest) throws NoAppellationException, NoNameException, InvalidPriceException, ExistingProblemException, StorageProblemException, MissingArgumentException, PersistenceException {
        accessoriesService.createAccessories(new Accessories(accessoriesRequest.getAppellation(),
                accessoriesRequest.getBrand(), accessoriesRequest.getPrice()));

    }

    @RequestMapping(value = "/updateaccessori", method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean update(@RequestBody AccessoriesRequest accessoriesRequest) throws NoAppellationException, NoNameException, InvalidPriceException, ExistingProblemException, StorageProblemException, MissingArgumentException, PersistenceException {
        return accessoriesService.updateAccessories(new Accessories(accessoriesRequest.getAppellation(),
                accessoriesRequest.getBrand(), accessoriesRequest.getPrice()));
    }

    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason = "Adatbázis hiba!")
    @ExceptionHandler({StorageProblemException.class})
    public void dataBaseError() {
    }

    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Nem adtál meg minden adatot!")
    @ExceptionHandler({NoAppellationException.class, NoNameException.class, InvalidPriceException.class, MissingArgumentException.class})
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
