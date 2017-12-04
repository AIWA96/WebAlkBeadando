package hu.iit.uni.miskolc.webalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.model.Employee;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.*;

import java.util.Collection;

public interface EmployeeDAO {

    void createEmployee(Employee employee) throws StorageNotAvailableException, StorageException, AlreadyExistingException, WrongFormatException;

    Employee getEmployee(int id) throws StorageNotAvailableException, StorageException, NotFoundException, WrongFormatException;

    Collection<Employee> getAllEmployee() throws StorageNotAvailableException, StorageException, NotFoundException, WrongFormatException;

    boolean updateEmployee(Employee employee) throws StorageNotAvailableException, StorageException, NotFoundException, AlreadyExistingException;

    boolean deleteEmployee(int id) throws StorageNotAvailableException, StorageException, NotFoundException;

    boolean deleteEmployee(Employee employee) throws StorageNotAvailableException, StorageException, NotFoundException;
}
