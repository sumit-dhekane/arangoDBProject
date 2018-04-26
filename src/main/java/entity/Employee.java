package entity;

public class Employee {
    private String department;
    private String empID;
    private String firstName;
    private String lastName;
    private String designation;
    private Double salary;

    public Employee(){
        this.department="";
        this.firstName="";
        this.lastName="";
        this.designation="";
        this.salary=0.0;

    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public Employee(String empID, String department, String firstName, String lastName, String designation, Double salary){
        this.empID=empID;

        this.department=department;
        this.firstName=firstName;
        this.lastName=lastName;
        this.designation=designation;
        this.salary=salary;

    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDesignation() {

        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDepartment() {

        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
