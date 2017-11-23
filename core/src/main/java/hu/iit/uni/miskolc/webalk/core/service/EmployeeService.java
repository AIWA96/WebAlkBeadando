package hu.iit.uni.miskolc.webalk.core.service;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Employee;

public interface EmployeeService {


    /**
     * @param idNum
     * @param name
     * @param gender
     * @param salary
     * @param post
     * @return
     * @throws AlreadyExistingException
     * @throws StorageNotAvailableException
     * @throws StorageException
     * @throws NotFoundException
     * @throws NoNameException
     */
    void createEmployee(Employee employee) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException, PersistanceException, WrongDataTypeException;

    /**
     * @param idNum
     * @return an Employee
     */
    Employee getEmployee(int idNum) throws WrongDataTypeException, NoPostException, NoGenderException, StorageException, NoNameException, PersistanceException, AlreadyExistingException, InvalidSalaryException;

    boolean updateEmployee(Employee employee) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException;

    boolean deleteEmployee(int idNum) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException;
    boolean deleteEmployee(Employee employee) throws ClassNotFoundException, AlreadyExistingException, StorageException, NotFoundException;
}
