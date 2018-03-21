package hu.iit.uni.miskolc.webalk.controller;

import hu.iit.uni.miskolc.webalk.controller.dto.EmployeeRequest;
import hu.iit.uni.miskolc.webalk.core.exceptions.InvalidSalaryException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoGenderException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoPostException;
import hu.iit.uni.miskolc.webalk.core.model.Employee;
import hu.iit.uni.miskolc.webalk.core.service.EmployeeService;
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

@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) throws CreateDataBaseException {
        this.employeeService = employeeService;
        if (!DataBase.isDBCreated()){
            throw new CreateDataBaseException(DataBase.getCause());
        }
    }

    @RequestMapping(value = {"/getemployees"}, method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<Employee> listAllEmployees() throws MissingArgumentException, PersistenceException, ExistingProblemException, StorageProblemException {
        return employeeService.getAllEmployee();
    }

    @RequestMapping(value = {"/getemployee/{id}"},
            method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Employee listEmployee(@PathVariable(value = "id") int id) throws MissingArgumentException, PersistenceException, ExistingProblemException, StorageProblemException {
        return employeeService.getEmployee(id);
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void add(@RequestBody EmployeeRequest employeeRequest) throws NoNameException, NoPostException, InvalidSalaryException, NoGenderException, ExistingProblemException, StorageProblemException, MissingArgumentException, PersistenceException {
        employeeService.createEmployee(new Employee(employeeRequest.getId(), employeeRequest.getName(),
                employeeRequest.getGender(), employeeRequest.getSalary(),
                employeeRequest.getPost(), employeeRequest.getShopName()));
    }

    @RequestMapping(value = "/updateemployee", method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean update(@RequestBody EmployeeRequest employeeRequest) throws NoNameException, NoPostException, InvalidSalaryException, NoGenderException, StorageProblemException, ExistingProblemException, PersistenceException {
        return employeeService.updateEmployee(new Employee(employeeRequest.getId(), employeeRequest.getName(),
                employeeRequest.getGender(), employeeRequest.getSalary(), employeeRequest.getPost(),
                employeeRequest.getShopName()));
    }

    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason = "Adatbázis hiba!")
    @ExceptionHandler({StorageProblemException.class})
    public void dataBaseError() {
    }

    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Nem adtál meg minden adatot!")
    @ExceptionHandler({NoNameException.class, NoPostException.class, InvalidSalaryException.class, NoGenderException.class, MissingArgumentException.class})
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
