package hu.iit.uni.miskolc.webalk.service;

import hu.iit.uni.miskolc.webalk.core.model.Employee;
import hu.iit.uni.miskolc.webalk.core.service.EmployeeService;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.ExistingProblemException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.MissingArgumentException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.StorageProblemException;
import hu.iit.uni.miskolc.webalk.service.dao.EmployeeDAO;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.*;

import java.util.Collection;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void createEmployee(Employee employee) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException {
        try {
            employeeDAO.createEmployee(employee);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (AlreadyExistingException e) {
            throw new ExistingProblemException();
        } catch (WrongFormatException e) {
            throw new MissingArgumentException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public Employee getEmployee(int idNum) throws PersistenceException, MissingArgumentException, ExistingProblemException, StorageProblemException {
        try {
            return employeeDAO.getEmployee(idNum);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (WrongFormatException e) {
            throw new MissingArgumentException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public Collection<Employee> getAllEmployee() throws PersistenceException, MissingArgumentException, ExistingProblemException, StorageProblemException {
        try {
            return employeeDAO.getAllEmployee();
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (WrongFormatException e) {
            throw new MissingArgumentException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public boolean updateEmployee(Employee employee) throws StorageProblemException, ExistingProblemException, PersistenceException {
        try {
            return employeeDAO.updateEmployee(employee);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException | AlreadyExistingException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public boolean deleteEmployee(int idNum) throws StorageProblemException, ExistingProblemException, PersistenceException {
        try {
            return employeeDAO.deleteEmployee(idNum);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }

    public boolean deleteEmployee(Employee employee) throws StorageProblemException, ExistingProblemException, PersistenceException {
        try {
            return employeeDAO.deleteEmployee(employee);
        } catch (StorageNotAvailableException | StorageException e) {
            throw new StorageProblemException();
        } catch (NotFoundException e) {
            throw new ExistingProblemException();
        } catch (Exception e) {
            throw new PersistenceException();
        }
    }
}
