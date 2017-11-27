package hu.uni.miskolc.webalk.controller;

import hu.iit.uni.miskolc.webalk.controller.dto.EmployeeRequest;
import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Employee;
import hu.iit.uni.miskolc.webalk.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = {"/getemployees"}, method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<Employee> listAllGlasses() {
        try {
            return employeeService.getAllEmployee();
        } catch (WrongDataTypeException e) {
            e.printStackTrace();
        } catch (NoPostException e) {
            e.printStackTrace();
        } catch (NoGenderException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (NoNameException e) {
            e.printStackTrace();
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (AlreadyExistingException e) {
            e.printStackTrace();
        } catch (InvalidSalaryException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = {"/getemployee/{id}"},
            method = {RequestMethod.GET},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Employee listEmployee(@PathVariable(value = "id") int id) {
        try {
            return employeeService.getEmployee(id);
        } catch (WrongDataTypeException e) {
            e.printStackTrace();
        } catch (NoPostException e) {
            e.printStackTrace();
        } catch (NoGenderException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (NoNameException e) {
            e.printStackTrace();
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (AlreadyExistingException e) {
            e.printStackTrace();
        } catch (InvalidSalaryException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void add(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = null;

        try {
            employee = new Employee(employeeRequest.getId(), employeeRequest.getName(),
                    employeeRequest.getGender(), employeeRequest.getSalary(),
                    employeeRequest.getPost(), employeeRequest.getShopName());
        } catch (NoNameException e) {
            e.printStackTrace();
        } catch (NoPostException e) {
            e.printStackTrace();
        } catch (InvalidSalaryException e) {
            e.printStackTrace();
        } catch (NoGenderException e) {
            e.printStackTrace();
        }

        try {
            employeeService.createEmployee(employee);
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
        }
    }
}
