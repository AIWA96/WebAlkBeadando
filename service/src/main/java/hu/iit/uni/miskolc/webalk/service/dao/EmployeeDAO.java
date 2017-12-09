package hu.iit.uni.miskolc.webalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.model.Employee;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.*;

import java.util.Collection;

public interface EmployeeDAO {

    void createEmployee(Employee employee) throws StorageNotAvailableException, StorageException, AlreadyExistException, PersistenceException;

    Employee getEmployee(int id) throws StorageNotAvailableException, StorageException, NotFoundException, PersistenceException, NoArgumentException;

    Collection<Employee> getAllEmployee() throws StorageNotAvailableException, StorageException, NotFoundException, NoArgumentException, PersistenceException;

    Collection<Employee> getEmployeeByShopName(String shopName) throws StorageNotAvailableException, StorageException, NotFoundException, NoArgumentException, PersistenceException;

    boolean updateEmployee(Employee employee) throws StorageNotAvailableException, StorageException, NotFoundException, AlreadyExistException, PersistenceException;

    boolean deleteEmployee(int id) throws StorageNotAvailableException, StorageException, NotFoundException, PersistenceException;

    boolean deleteEmployee(Employee employee) throws StorageNotAvailableException, StorageException, NotFoundException, PersistenceException;
}
