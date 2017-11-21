package hu.iit.uni.miskolc.wabalk.dao;

import hu.iit.uni.miskolc.wabalk.service.dao.EmployeeDAO;
import hu.iit.uni.miskolc.webalk.core.model.Employee;

public class EmloyeeDAOsql implements EmployeeDAO {
    @Override
    public void createEmployee(Employee employee) {

    }

    @Override
    public Employee getEmployee(int id) {
        return null;
    }

    @Override
    public boolean updateEmployee(int id) {
        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return false;
    }

    @Override
    public boolean deleteEmployee(int id) {
        return false;
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        return false;
    }
}
