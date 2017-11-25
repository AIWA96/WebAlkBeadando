package hu.iit.uni.miskolc.wabalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Employee;

import java.util.Collection;

public interface EmployeeDAO {
    void createEmployee(Employee employee) throws AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException;

    Employee getEmployee(int id) throws NoNameException, NoPostException, InvalidSalaryException, NoGenderException, AlreadyExistingException, WrongDataTypeException, StorageException, PersistanceException;
    Collection<Employee> getAllEmployee() throws WrongDataTypeException, NoPostException, NoGenderException, StorageException, NoNameException, PersistanceException, AlreadyExistingException, InvalidSalaryException;

    boolean updateEmployee(Employee employee) throws ClassNotFoundException, NotFoundException, AlreadyExistingException, StorageException;

    boolean deleteEmployee(int id) throws ClassNotFoundException, NotFoundException, AlreadyExistingException, StorageException;
    boolean deleteEmployee(Employee employee) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException;
}
