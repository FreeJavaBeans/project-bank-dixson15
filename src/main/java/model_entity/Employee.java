package model_entity;

abstract class Employee implements Person{

    private int emp_id;
    private  String firstName;
    private String lastName;

    public Employee(int id, String firstName, String lastName) {
        this.emp_id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getEmp_id() {
        return emp_id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}
