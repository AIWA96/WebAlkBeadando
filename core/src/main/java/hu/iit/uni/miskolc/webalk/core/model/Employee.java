package hu.iit.uni.miskolc.webalk.core.model;

import hu.iit.uni.miskolc.webalk.core.exceptions.InvalidSalaryException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoGenderException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoPostException;
import org.jetbrains.annotations.Contract;

public class Employee {
    private int idNum;
    private String name;
    private String gender;
    private float salary;
    private String post;
    private String shopName;

    /**
     * @param idNum
     * @param name
     * @param gender
     * @param salary
     * @param post
     * @param shopName
     * @throws NoNameException
     * @throws NoPostException
     * @throws InvalidSalaryException
     * @throws NoGenderException
     */
    public Employee(int idNum, String name, String gender, float salary, String post, String shopName) throws NoNameException, NoPostException, InvalidSalaryException, NoGenderException {
        setIdNum(idNum);
        setShopName(shopName);
        setName(name);
        setGender(gender);
        setSalary(salary);
        setPost(post);
    }

    public int getIdNum() {
        return idNum;
    }

    private void setIdNum(int idNum) {
        this.idNum = idNum;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) throws NoGenderException {
        if (gender == null) {
            throw new NoGenderException("The gender must have set for a person!");
        }
        this.gender = gender;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) throws InvalidSalaryException {
        if (salary < 85000.0f) {
            throw new InvalidSalaryException("The salary cannot be less then the minimum wage!");
        }
        this.salary = salary;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) throws NoPostException {
        if (post == null) {
            throw new NoPostException("An employee must have a post!");
        }
        this.post = post;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) throws NoNameException {
        if (shopName == null || shopName.equals("")) {
            throw new NoNameException("Shop name where the employee is working must be set!");
        }
        this.shopName = shopName;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return idNum == employee.idNum &&
                Float.compare(employee.salary, salary) == 0 && name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        int result = idNum;
        result = 31 * result + name.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + (salary != +0.0f ? Float.floatToIntBits(salary) : 0);
        result = 31 * result + post.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idNum=" + idNum +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", salary=" + salary +
                ", post='" + post + '\'' +
                ", shopName='" + shopName + '\'' +
                "}\n";
    }
}
