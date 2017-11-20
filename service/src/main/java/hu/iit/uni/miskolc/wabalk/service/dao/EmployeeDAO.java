package hu.iit.uni.miskolc.wabalk.service.dao;

import hu.iit.uni.miskolc.webalk.core.model.Employee;

public interface EmployeeDAO {
    void createEmployee(Employee employee);

    Employee getEmployee(int id);

    boolean updateEmployee(int id);
    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(int id);
    boolean deleteEmployee(Employee employee);
}
