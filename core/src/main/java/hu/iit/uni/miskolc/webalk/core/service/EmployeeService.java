package hu.iit.uni.miskolc.webalk.core.service;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Employee;

import java.util.Collection;

public interface EmployeeService {


    /**
     * @param employee
     * @throws AlreadyExistingException
     * @throws StorageNotAvailableException
     * @throws StorageException
     * @throws NotFoundException
     * @throws NoNameException
     * @throws PersistenceException
     * @throws WrongDataTypeException
     */
    void createEmployee(Employee employee) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException, PersistenceException, WrongDataTypeException;

    /**
     * @param idNum
     * @return an Employee
     */
    Employee getEmployee(int idNum) throws WrongDataTypeException, NoPostException, NoGenderException, StorageException, NoNameException, PersistenceException, AlreadyExistingException, InvalidSalaryException;
    Collection<Employee> getAllEmployee() throws WrongDataTypeException, NoPostException, NoGenderException, StorageException, NoNameException, PersistenceException, AlreadyExistingException, InvalidSalaryException;

    boolean updateEmployee(Employee employee) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException;

    boolean deleteEmployee(int idNum) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException;
    boolean deleteEmployee(Employee employee) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException;
}
