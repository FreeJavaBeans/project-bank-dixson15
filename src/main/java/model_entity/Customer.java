package model_entity;

public class Customer extends Person{

    private int customer_id;

    public Customer(String firstName, String lastName, int customer_id) {
        super(firstName, lastName);
        this.customer_id = customer_id;
    }

    public Customer() {
        super();
    }

    /**
     *
     * @return customer id
     */
    public int getCustomer_id() {
        return customer_id;
    }
}
