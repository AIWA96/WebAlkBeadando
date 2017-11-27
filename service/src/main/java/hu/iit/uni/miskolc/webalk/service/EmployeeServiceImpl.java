package hu.iit.uni.miskolc.webalk.service;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Employee;
import hu.iit.uni.miskolc.webalk.core.service.EmployeeService;
import hu.iit.uni.miskolc.webalk.service.dao.EmployeeDAO;

import java.util.Collection;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void createEmployee(Employee employee) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException, PersistenceException, WrongDataTypeException {
        employeeDAO.createEmployee(employee);
    }

    public Employee getEmployee(int idNum) throws WrongDataTypeException, NoPostException, NoGenderException, StorageException, NoNameException, PersistenceException, AlreadyExistingException, InvalidSalaryException {
        return employeeDAO.getEmployee(idNum);
    }

    public Collection<Employee> getAllEmployee() throws WrongDataTypeException, NoPostException, NoGenderException, StorageException, NoNameException, PersistenceException, AlreadyExistingException, InvalidSalaryException {
        return employeeDAO.getAllEmployee();
    }

    public boolean updateEmployee(Employee employee) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException {
        return employeeDAO.updateEmployee(employee);
    }

    public boolean deleteEmployee(int idNum) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException {
        return employeeDAO.deleteEmployee(idNum);
    }

    public boolean deleteEmployee(Employee employee) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException {
        return employeeDAO.deleteEmployee(employee);
    }
}
