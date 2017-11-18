package hu.iit.uni.miskolc.webalk.core.service;

import hu.iit.uni.miskolc.webalk.core.exceptions.*;
import hu.iit.uni.miskolc.webalk.core.model.Post;
import hu.iit.uni.miskolc.webalk.core.model.Sex;
import hu.iit.uni.miskolc.webalk.core.model.Employee;

public interface EmployeeService {


    /**
     * @param idNum
     * @param name
     * @param sex
     * @param salary
     * @param post
     * @return
     * @throws AlreadyExistingException
     * @throws StorageNotAvailableException
     * @throws StorageException
     * @throws NotFoundException
     * @throws NoNameException
     */
    Employee createEmployee(int idNum, String name, Sex sex, float salary, Post post) throws AlreadyExistingException, StorageNotAvailableException, StorageException, NotFoundException, NoNameException;

    /**
     * @param idNum
     * @return an Employee
     */
    Employee getEmployee(int idNum);

    /**
     * @param name
     * @return an Employee
     */
    Employee getEmployee(String name);

    boolean updateEmployee(int idNum);
    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(int idNum);
    boolean deleteEmployee(Employee employee);
}
