package hu.iit.uni.miskolc.webalk.core.model;

import hu.iit.uni.miskolc.webalk.core.exceptions.NoEmployeeException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoLocationException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;

import java.util.Collection;

public class Shop {
    private String name;
    private String location;
    private Collection<Employee> employees;

    /**
     * @param name
     * @param location
     * @param employees
     * @throws NoNameException
     * @throws NoLocationException
     * @throws NoEmployeeException
     */
    public Shop(String name, String location, Collection<Employee> employees) throws NoNameException, NoLocationException, NoEmployeeException {
        setName(name);
        setLocation(location);
        setEmployees(employees);
    }

    /**
     * Gets the number of employees in the shop
     *
     * @return result - the number of employees
     */
    public int getNumberOfEmployees() {
        int result;
        result = employees.size();
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NoNameException {
        if (name == null) {
            throw new NoNameException("A shop must have a name!");
        }
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) throws NoLocationException {
        if (location == null) {
            throw new NoLocationException("A shop must have a location!");
        }
        this.location = location;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) throws NoEmployeeException {
        if (employees == null || employees.isEmpty() == true) {
            throw new NoEmployeeException("A shop must contain one employee!");
        }
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        if (!name.equals(shop.name)) return false;
        if (!location.equals(shop.location)) return false;
        return employees.equals(shop.employees);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + employees.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", employees=" + employees +
                "}\n";
    }
}
