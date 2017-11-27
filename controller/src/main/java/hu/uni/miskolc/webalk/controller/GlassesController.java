package hu.uni.miskolc.webalk.controller;

import hu.iit.uni.miskolc.webalk.core.exceptions.AlreadyExistingException;
import hu.iit.uni.miskolc.webalk.core.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.core.exceptions.StorageException;
import hu.iit.uni.miskolc.webalk.core.exceptions.WrongDataTypeException;
import hu.iit.uni.miskolc.webalk.core.model.Glasses;
import hu.iit.uni.miskolc.webalk.core.service.GlassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
