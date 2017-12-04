package hu.iit.uni.miskolc.webalk.core.service;

import hu.iit.uni.miskolc.webalk.core.model.Employee;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.ExistingProblemException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.MissingArgumentException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.PersistenceException;
import hu.iit.uni.miskolc.webalk.core.service.exceptions.StorageProblemException;

import java.util.Collection;

public interface EmployeeService {

    void createEmployee(Employee employee) throws StorageProblemException, ExistingProblemException, MissingArgumentException, PersistenceException;

    Employee getEmployee(int idNum) throws PersistenceException, MissingArgumentException, ExistingProblemException, StorageProblemException;

    Collection<Employee> getAllEmployee() throws PersistenceException, MissingArgumentException, ExistingProblemException, StorageProblemException;

    boolean updateEmployee(Employee employee) throws StorageProblemException, ExistingProblemException, PersistenceException;

    boolean deleteEmployee(int idNum) throws StorageProblemException, ExistingProblemException, PersistenceException;

    boolean deleteEmployee(Employee employee) throws StorageProblemException, ExistingProblemException, PersistenceException;
}
