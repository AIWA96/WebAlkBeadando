package hu.iit.uni.miskolc.webalk.core.model;

import hu.iit.uni.miskolc.webalk.core.exceptions.InvalidSalaryException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoNameException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoPostException;
import hu.iit.uni.miskolc.webalk.core.exceptions.NoSexException;

public class Employee {
    private int idNum;
    private String name;
    private Sex sex;
    private float salary;
    private Post post;

    /**
     * @param idNum
     * @param name
     * @param sex
     * @param salary
     * @param post
     * @throws NoNameException
     * @throws NoPostException
     * @throws InvalidSalaryException
     * @throws NoSexException
     */
    public Employee(int idNum, String name, Sex sex, float salary, Post post) throws NoNameException, NoPostException, InvalidSalaryException, NoSexException {
        this.idNum = idNum;
        setName(name);
        setSex(sex);
        setSalary(salary);
        setPost(post);
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NoNameException {
        if (name == null){
            throw new NoNameException("A shop must have a name!");
        }
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) throws NoSexException {
        if (sex == null){
            throw new NoSexException("The sex mast have set for a person!");
        }
        this.sex = sex;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) throws InvalidSalaryException {
        if (salary < 85000.0f){
            throw new InvalidSalaryException("The salary cannot be less then the minimum wage!");
        }
        this.salary = salary;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) throws NoPostException {
        if (post == null){
            throw new NoPostException("An employee must have a post!");
        }
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (idNum != employee.idNum) return false;
        if (Float.compare(employee.salary, salary) != 0) return false;
        if (!name.equals(employee.name)) return false;
        if (sex != employee.sex) return false;
        return post == employee.post;
    }

    @Override
    public int hashCode() {
        int result = idNum;
        result = 31 * result + name.hashCode();
        result = 31 * result + sex.hashCode();
        result = 31 * result + (salary != +0.0f ? Float.floatToIntBits(salary) : 0);
        result = 31 * result + post.hashCode();
        return result;
    }
}
