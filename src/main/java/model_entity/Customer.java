package model_entity;

public class Customer extends Employee{

    public Customer(int id,String firstName, String lastName) {
        super(id,firstName, lastName);
    }

    public Customer() {
        super(0,null, null);
    }
}
